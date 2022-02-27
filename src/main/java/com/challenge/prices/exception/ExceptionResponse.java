package com.challenge.prices.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
  private String errorMessage;
  private String details;
  private Integer errorCode;

  public ExceptionResponse(String errorMessage, String details, Integer errorCode) {
    this.errorMessage = errorMessage;
    this.details = details;
    this.errorCode = errorCode;
  }
}
