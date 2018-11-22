/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.project.errors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 *
 * @author P061
 */
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
class ApiError {

  private HttpStatus status;
  private String message;
  private String debugMessage;
  private List<ApiSubError> subErrors;

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDebugMessage() {
    return debugMessage;
  }

  public void setDebugMessage(String debugMessage) {
    this.debugMessage = debugMessage;
  }

  public List<ApiSubError> getSubErrors() {
    return subErrors;
  }

  public void setSubErrors(
      List<ApiSubError> subErrors) {
    this.subErrors = subErrors;
  }

  private ApiError() {
  }

  ApiError(HttpStatus status) {
    this();
    this.status = status;
  }

  ApiError(HttpStatus status, Throwable ex) {
    this();
    this.status = status;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
  }

  ApiError(HttpStatus status, String message, Throwable ex) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
  }

  private void addSubError(ApiSubError subError) {
    if (subErrors == null) {
      subErrors = new ArrayList<>();
    }
    subErrors.add(subError);
  }


  private String generateMessage(String fieldComplete, String code) {

    String[] parts, parts2, parts3;
    String field;
    String secondLevel = "";
    String thirdLevel = "";
    String point = ".";
    int orderSecondLevel = -1;
    int orderThirdLevel = -1;
    parts = fieldComplete.split("\\.");
    if (parts.length == 1) {
      field = parts[0];
    } else if (parts.length == 2) {
      field = parts[1];
      parts2 = parts[0].split("\\[");
      secondLevel = parts2[0];
      parts3 = parts2[1].split("\\]");
      orderSecondLevel = Integer.parseInt(parts3[0]) + 1;
    } else {
      field = parts[2];
      parts2 = parts[0].split("\\[");
      secondLevel = parts2[0];
      parts3 = parts2[1].split("\\]");
      orderSecondLevel = Integer.parseInt(parts3[0]) + 1;
      parts2 = parts[1].split("\\[");
      thirdLevel = parts2[0];
      parts3 = parts2[1].split("\\]");
      orderThirdLevel = Integer.parseInt(parts3[0]) + 1;
    }
    parts = code.split("\\.");
    if(parts.length > 1) {
      code = parts[parts.length - 1];
    }
    if(orderSecondLevel >= 0){
      secondLevel = secondLevel + point + orderSecondLevel + point;
      if(orderThirdLevel >= 0){
        thirdLevel = thirdLevel + point + orderThirdLevel + point;
      }
    }

    return secondLevel + thirdLevel + field + point + code;
  }

  private void addValidationError(String object, String message) {
    addSubError(new ApiValidationError(object, message));
  }

  void addValidationErrors(List<FieldError> fieldErrors) {
    fieldErrors.forEach(this::addValidationError);
  }

  private void addValidationError(ObjectError objectError) {
    this.addValidationError(
        objectError.getObjectName(),
        objectError.getDefaultMessage());
  }

  void addValidationError(List<ObjectError> globalErrors) {
    globalErrors.forEach(this::addValidationError);
  }

  /**
   * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation
   * fails.
   *
   * @param cv the ConstraintViolation
   */

  abstract class ApiSubError {

  }

  class ApiValidationError extends ApiSubError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
    private String code;

    ApiValidationError(String object, String message) {
      this.object = object;
      this.message = message;
    }
  }
}

class LowerCaseClassNameResolver extends TypeIdResolverBase {

  @Override
  public String idFromValue(Object value) {
    return value.getClass().getSimpleName().toLowerCase();
  }

  @Override
  public String idFromValueAndType(Object value, Class<?> suggestedType) {
    return idFromValue(value);
  }

  @Override
  public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.CUSTOM;
  }
}