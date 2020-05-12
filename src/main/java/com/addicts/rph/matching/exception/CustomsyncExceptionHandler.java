package com.addicts.rph.matching.exception;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 * @author sravantatikonda
 */
@Slf4j
public class CustomsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

  @Override
  public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {

    log.error("Exception Cause - " + throwable.getMessage());
    log.error("Method name - " + method.getName());
    for (Object param : obj) {
      log.error("Parameter value - " + param);
    }
  }
}