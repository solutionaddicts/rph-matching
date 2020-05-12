package com.addicts.rph.matching.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author sravantatikonda
 */
@SpringBootConfiguration
@EnableConfigurationProperties({AmazonClientConfig.class, PropsConfig.class, MailConfig.class})
public class MessagesConfig {

  @Autowired
  private AmazonClientConfig amazonClientConfig;

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public LocalValidatorFactoryBean validator() {
    LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
    factoryBean.setValidationMessageSource(messageSource());
    return factoryBean;
  }

  @Bean
  public AmazonClientConfig amazonClientConfig() {
    return new AmazonClientConfig();
  }

  @Bean
  @Primary
  public AmazonS3 amazonS3Client() {
    BasicAWSCredentials awsCreds = new BasicAWSCredentials(amazonClientConfig.getAccessKey(),
        amazonClientConfig.getSecretKey());
    return AmazonS3ClientBuilder.standard()
        .withRegion(Regions.fromName(amazonClientConfig.getRegion()))
        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
        .build();
  }
}
