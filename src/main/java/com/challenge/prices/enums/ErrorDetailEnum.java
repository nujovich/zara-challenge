package com.challenge.prices.enums;

import lombok.Getter;

@Getter
public enum ErrorDetailEnum {
  DATETIME_PARSE_EXCEPTION_DETAIL_ERROR("Datetime must follow ISO format: yyyy-MM-ddTHH:MM"),
  METHOD_ARGUMENT_TYPE_MISMATCH_DETAIL_ERROR("Required parameter with wrong format"),
  ENTITY_NOT_FUND_DETAIL_ERROR("Price not found for the selected date, product id and brand id");

  private String detail;

  ErrorDetailEnum(String detail) {
    this.detail = detail;
  }
}
