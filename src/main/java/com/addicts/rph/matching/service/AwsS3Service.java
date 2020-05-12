package com.addicts.rph.matching.service;

import com.addicts.rph.matching.exception.RphException;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sravantatikonda
 */
public interface AwsS3Service {

  List<String> uploadMultipleFiles(Long listingId, List<MultipartFile> files)
      throws RphException;

  ResponseEntity<byte[]> downloadFileFromS3bucket(String fileName) throws RphException, IOException;

  void deleteFileFromS3bucket(Long listingId, String fileName) throws RphException;

  void deleteFilesFromS3bucket(Long listingId, List<String> delImgUrls) throws RphException;

  PutObjectResult uploadBlog(MultipartFile blog) throws RphException, IOException;
}
