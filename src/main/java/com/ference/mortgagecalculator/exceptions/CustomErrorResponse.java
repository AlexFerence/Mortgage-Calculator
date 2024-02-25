package com.ference.mortgagecalculator.exceptions;

import java.util.List;

public class CustomErrorResponse {
  String timestamp;
  int status;
  String message;
  List<String> errors;

  public CustomErrorResponse(String timestamp, int status, String message) {
    this.timestamp = timestamp;
    this.status = status;
    this.message = message;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }
}
