package com.addicts.rph.matching.service.impl;

import com.addicts.rph.matching.config.AmazonClientConfig;
import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.exception.RphValidationException;
import com.addicts.rph.matching.service.AwsS3Service;
import com.addicts.rph.matching.util.ErrorMessages;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sravantatikonda
 */
@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class AwsS3S3ServiceImpl implements AwsS3Service {

  @Autowired
  private AmazonS3 amazonS3Client;

  @Autowired
  private AmazonClientConfig amazonClientConfig;

  @Autowired
  private ErrorMessages errorMessages;

  /**
   * Re-sizes an input based on the percent, defaulted to 50% of current size(0.5)
   *
   * @param inputFile the value for {@link File}
   * @return the resized value for @{@link File}
   * @throws IOException
   */
  private static Map<File, ByteArrayInputStream> resize(File inputFile, Long listingId)
      throws RphException {
    ByteArrayInputStream resizedInput = null;
    try {
      BufferedImage inputImage = ImageIO.read(inputFile);
      if (inputImage != null) {
        int scaledWidth = (int) (inputImage.getWidth() * 0.5);
        int scaledHeight = (int) (inputImage.getHeight() * 0.5);
        BufferedImage outputImage = new BufferedImage(scaledWidth,
            scaledHeight, inputImage.getType());
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, "jpg", baos);
        byte[] buffer = baos.toByteArray();
        resizedInput = new ByteArrayInputStream(buffer);
      }
    } catch (IOException e) {
      log.error("Error while resizing an image to be uploaded to S3 bucket for listing: {}",
          listingId);
      throw new RphException(
          "Resize Error- Error while resizing an image to be uploaded to S3 bucket");
    }

    return Collections.singletonMap(inputFile, resizedInput);
  }

  /**
   * Method to convert a {@link MultipartFile} to {@link File} type
   *
   * @param file the value for {@link File}
   * @return the value for {@link File}
   */
  private File convertMultiPartFileToFile(MultipartFile file) {
    File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      log.error("Error converting multipartFile to file", e);
    }
    return convertedFile;
  }

  /**
   * Uploads a list of image files to S3 bucket's listing specific folder
   *
   * @param listingId      the value for unique listingId which is returned after listing submitted
   *                       for review
   * @param multipartFiles the list of files to be uploaded to S3 bucket
   */
  @Override
  public List<String> uploadMultipleFiles(Long listingId, List<MultipartFile> multipartFiles)
      throws RphException {
    if (listingId == null) {
      throw new RphValidationException(errorMessages.getErrorMessages().get("NULL_LISTID"));
    }
    Map<File, ByteArrayInputStream> files = new HashMap<>();
    List<String> filePaths = new ArrayList<>();
    for (MultipartFile multipartFile : multipartFiles) {
      File file = convertMultiPartFileToFile(multipartFile);
      filePaths
          .add(amazonClientConfig.getEndpointUrl() + "/" + amazonClientConfig.getImgFolder() + "/"
              + listingId + "/" + file.getName());
      // Resize the image to reduce the size
      Map<File, ByteArrayInputStream> resizedInput = resize(file, listingId);
      files.putAll(resizedInput);
    }

    uploadToS3Bucket(listingId, files);
    // files.forEach(file -> file.delete());

    return filePaths;
  }

  @Async("threadPoolTaskExecutor")
  void uploadToS3Bucket(Long listingId, Map<File, ByteArrayInputStream> files) {
    files.forEach((file, byteInput) -> {
      ObjectMetadata meta = new ObjectMetadata();
      meta.setContentType("image/" + FilenameUtils.getExtension(file.getName()));
      meta.setContentLength(byteInput.available());
      amazonS3Client.putObject(new PutObjectRequest(
          amazonClientConfig
              .getAssetsBucketName()
              .concat("/").concat(amazonClientConfig.getImgFolder())
              .concat("/").concat(listingId.toString()),
          file.getName(),
          byteInput,
          meta));
    });

//    TransferManager transferManager = TransferManagerBuilder
//        .standard()
//        .withMultipartUploadThreshold(1024L * 1024)
//        .withS3Client(amazonS3Client)
//        .build();
//    try {
//      MultipleFileUpload multipleFileUpload = transferManager
//          .uploadFileList(amazonClientConfig.getAssetsBucketName(),
//              amazonClientConfig.getImgFolder() + "/" + listingId.toString(),
//              new File("."), files);
//      XferMgrProgress.showTransferProgress(multipleFileUpload);
//      XferMgrProgress.waitForCompletion(multipleFileUpload);
//      files.forEach(File::delete);
//    } catch (AmazonServiceException e) {
//      log.error(e.getErrorMessage());
//    }
//
//    transferManager.shutdownNow(false);
  }

  /**
   * Deletes an image file from S3 bucket's listing specific folder
   *
   * @param listingId the value for unique listingId which is returned after listing submitted for
   *                  review
   * @param fileName  the name of the file to be deleted from S3 bucket
   */
  @Override
  public void deleteFileFromS3bucket(Long listingId, String fileName) {
    amazonS3Client
        .deleteObject(amazonClientConfig
            .getAssetsBucketName()
            .concat("/").concat(amazonClientConfig.getImgFolder())
            .concat("/").concat(listingId.toString()), fileName);
  }

  /**
   * Deletes all image files from S3 bucket's listing specific folder
   *
   * @param listingId the value for unique listingId which is returned after listing submitted for
   *                  review
   */
  @Override
  public void deleteFilesFromS3bucket(Long listingId, List<String> delImgUrls) {
    List<String> delImagesNames = delImgUrls.stream()
        .map(delImgUrl -> delImgUrl.substring(delImgUrl.lastIndexOf('/') + 1)).collect(
            Collectors.toList());

    List<S3ObjectSummary> imageFiles = amazonS3Client
        .listObjects(amazonClientConfig.getAssetsBucketName(), "images/" + listingId)
        .getObjectSummaries();

    for (S3ObjectSummary file : imageFiles) {
      String imageTobeDeleted = file.getKey().substring(file.getKey().lastIndexOf('/') + 1);
      if (delImagesNames.contains(imageTobeDeleted)) {
        amazonS3Client.deleteObject(amazonClientConfig.getAssetsBucketName(), file.getKey());
      }
    }
  }

  @Override
  public PutObjectResult uploadBlog(MultipartFile blog) throws RphException, IOException {
    InputStream inputStream = new BufferedInputStream(blog.getInputStream());

    PutObjectRequest putObjectRequest = new PutObjectRequest(
        amazonClientConfig.getAssetsBucketName(), amazonClientConfig.getBlogFolder(), inputStream,
        new ObjectMetadata());

    putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

    PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
    IOUtils.closeQuietly(inputStream);

    return putObjectResult;
  }

  /**
   * Retrieves an image file from S3 bucket's listing specific folder
   *
   * @param blog the name of the file to be retrieved from S3 bucket
   */
  @Override
  public ResponseEntity<byte[]> downloadFileFromS3bucket(String blog) throws IOException {
    GetObjectRequest getObjectRequest = new GetObjectRequest(
        amazonClientConfig.getAssetsBucketName(),
        amazonClientConfig.getBlogFolder().concat("/") + blog);

    S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

    S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

    byte[] bytes = IOUtils.toByteArray(objectInputStream);

    String fileName = URLEncoder.encode(blog, "UTF-8")
        .replaceAll("\\+", "%20");

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    httpHeaders.setContentLength(bytes.length);
    httpHeaders.setContentDispositionFormData("attachment", fileName);

    return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);

    /*return amazonS3Client
        .getObject(amazonClientConfig.getAssetsBucketName(),
            amazonClientConfig.getBlogFolder().concat("/").concat(fileName));*/
  }

}
