package com.addicts.rph.matching.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sravantatikonda
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "amazonproperties")
public class AmazonClientConfig {

  private String endpointUrl;

  private String assetsBucketName;

  private String imgFolder;

  private String blogFolder;

  private String vidFolder;

  private String region;

  private String accessKey;

  private String secretKey;
}
