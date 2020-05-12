package com.addicts.rph.matching.controller;

import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.service.AwsS3Service;
import com.amazonaws.services.s3.model.PutObjectResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sravantatikonda
 */
@RestController
@Api(value = "AWS_S3", description = "This controller contains endpoints for managing and retrieving S3 bucket's folder images for listings")
@RequestMapping("s3/sec/")
public class AwsS3Controller {

  @Autowired
  private AwsS3Service awsService;

  /**
   * Deletes an image file from S3 bucket's listing specific folder
   *
   * @param listingId the value for unique listingId which is returned after listing submitted for
   *                  review
   * @param fileName  the name of the file to be deleted from S3 bucket
   */
  @DeleteMapping("images/{listingId}/{fileName}")
  public void deleteFile(@PathVariable Long listingId, @PathVariable String fileName)
      throws RphException {
    this.awsService.deleteFileFromS3bucket(listingId, fileName);
  }

  /**
   * Deletes all image files from S3 bucket's listing specific folder
   *
   * @param listingId the value for unique listingId which is returned after listing submitted for
   *                  review
   */
  @DeleteMapping("/images/{listingId}}")
  public void deleteFiles(@PathVariable Long listingId,
      @RequestParam(required = false) List<String> delImgUrls) throws RphException {
    this.awsService.deleteFilesFromS3bucket(listingId, delImgUrls);
  }

  /**
   * Upload a blog file to S3 bucket
   *
   * @param blog the file to be uploaded to S3 bucket
   */
  @ApiOperation(value = "uploadBlog", notes = "This API will updates a draft listing of a user")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 428, message = "Precondition Required")
  })
  @PostMapping(value = "blog/internal/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public PutObjectResult uploadBlog(
      @RequestPart(name = "blog", required = false) MultipartFile blog)
      throws RphException, IOException {
    return this.awsService.uploadBlog(blog);
  }

  /**
   * Retrieves an image file from S3 bucket's listing specific folder
   *
   * @param fileName the name of the file to be retrieved from S3 bucket
   */
  @GetMapping("blog/")
  public ResponseEntity<byte[]> getFile(@RequestParam String fileName)
      throws RphException, IOException {
    return this.awsService.downloadFileFromS3bucket(fileName);
  }
}
