package com.addicts.rph.matching.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

/**
 * @author sravantatikonda
 */
@ConfigurationProperties("rph.file-name")
@Component
public class ErrorMessages {

  private static final Map<String, String> errorMessages = new HashMap<>();

  public Map<String, String> getErrorMessages() {
    if (!errorMessages.isEmpty()) {
      return errorMessages;
    }
    synchronized (this) {
      if (errorMessages.isEmpty()) {
        loadErrorMessages();
      }
      return errorMessages;
    }
  }

  private void loadErrorMessages() {
    Yaml yaml = new Yaml();
    errorMessages.putAll(yaml.load(getFileStreamFromRes()));
  }

  private InputStream getFileStreamFromRes() {
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("error-messages.yml");
    if (inputStream == null) {
      throw new IllegalArgumentException("file is not found!");
    }
    return inputStream;
  }

}
