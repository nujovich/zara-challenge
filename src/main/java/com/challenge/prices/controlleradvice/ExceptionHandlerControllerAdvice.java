package com.challenge.prices.controlleradvice;


import com.challenge.prices.enums.ErrorDetailEnum;
import com.challenge.prices.exception.ExceptionResponse;
import java.time.format.DateTimeParseException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

  @ExceptionHandler({DateTimeParseException.class, NumberFormatException.class})
  public ResponseEntity handleDatetimeFormatException(
      final Exception exception,
      final WebRequest request) {
      String details = exception instanceof DateTimeParseException ?
          ErrorDetailEnum.DATETIME_PARSE_EXCEPTION_DETAIL_ERROR.getDetail() :
          ErrorDetailEnum.METHOD_ARGUMENT_TYPE_MISMATCH_DETAIL_ERROR.getDetail();
      final ExceptionResponse ex =  new ExceptionResponse(exception.getMessage(),
          details,
          HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity  handleException(
      final Exception exception,
      final WebRequest request) {
    final ExceptionResponse ex = new ExceptionResponse(exception.getMessage(),
        "Price not found for the selected date, product id and brand id",
        HttpStatus.NOT_FOUND.value());
    return new ResponseEntity(ex, HttpStatus.NOT_FOUND);
  }

}
