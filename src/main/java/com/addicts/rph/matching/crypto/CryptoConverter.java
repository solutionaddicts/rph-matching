package com.addicts.rph.matching.crypto;

import io.micrometer.core.instrument.util.StringUtils;
import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author sravantatikonda
 */
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {


  @Value("${encrypt.db_columns.algorithm}")
  private String algorithm;

  @Value("${encrypt.db_columns.key}")
  private String encryptionKey;

  @Override
  public String convertToDatabaseColumn(String plainField) {
    if (StringUtils.isNotBlank(plainField)) {
      Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
      try {
        Cipher c = Cipher.getInstance(algorithm);
        c.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(c.doFinal(plainField.getBytes()));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return plainField;
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    if (StringUtils.isNotBlank(dbData)) {
      Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
      try {
        Cipher c = Cipher.getInstance(algorithm);
        c.init(Cipher.DECRYPT_MODE, key);
        return new String(c.doFinal(Base64.getDecoder().decode(dbData)));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return dbData;
  }
}