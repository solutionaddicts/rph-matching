package com.addicts.rph.matching.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "appconfig")
public class PropsConfig {

  private String utilPath;

  private String imagesPath;
}
