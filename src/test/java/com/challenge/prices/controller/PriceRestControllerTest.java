package com.challenge.prices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.challenge.prices.entity.Price;
import com.challenge.prices.service.PriceService;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PriceRestControllerTest {

  private PriceRestController priceRestController;

  @Mock
  private PriceService priceService;

  private List<Price> prices;
  private Optional<List<Price>> optionalPrices;

  @BeforeEach
  public void setUp(){
    this.priceRestController = new PriceRestController(priceService);
    Price price = Mockito.mock(Price.class);
    Price anotherPrice = Mockito.mock(Price.class);
    this.prices = new ArrayList<>();
    this.prices.add(price);
    this.prices.add(anotherPrice);
    this.optionalPrices = Optional.of(prices);
  }

  @Test
  public void givenBadFormatOfDatetimeShouldReturnErrorResponse(){
    DateTimeParseException ex = assertThrows(DateTimeParseException.class, () -> {
      priceRestController.getPrice("2020-06-14 10:00", Long.valueOf(1), Long.valueOf(1));
    });

    assertNotNull(ex.getMessage());
  }

  @Test
  public void givenBadFormatOfProductIdShouldReturnErrorResponse(){
    NumberFormatException ex = assertThrows(NumberFormatException.class, () -> {
      this.priceRestController.getPrice("2020-06-14T10:00", Long.valueOf("ABC"), Long.valueOf(1)) ;
    });

    assertNotNull(ex.getMessage());
  }

  @Test
  public void givenValidParamsShouldReturnErrorResponse(){
    Mockito.when(priceService.getPrices(LocalDateTime.parse("2020-06-14T10:00"), Long.valueOf(1), Long.valueOf(1))).thenReturn(Optional.empty());
    EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
      this.priceRestController.getPrice("2020-06-14T10:00", Long.valueOf(1), Long.valueOf(1)) ;
    });

    assertNotNull(ex.getMessage());
  }

  @Test
  public void givenValidParamsShouldReturnResponse() {
    Mockito.when(priceService.getPrices(LocalDateTime.parse("2020-06-14T10:00"), Long.valueOf(1), Long.valueOf(1))).thenReturn(
        optionalPrices);

    ResponseEntity response = this.priceRestController.getPrice("2020-06-14T10:00", Long.valueOf(1), Long.valueOf(1));

    assertNotNull(response.getBody());
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }

}
