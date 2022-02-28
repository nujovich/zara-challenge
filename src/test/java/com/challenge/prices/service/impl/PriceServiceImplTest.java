package com.challenge.prices.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.challenge.prices.entity.Price;
import com.challenge.prices.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

  private PriceServiceImpl priceServiceImpl;

  @Mock
  private PriceRepository priceRepository;

  private List<Price> prices;

  @BeforeEach
  public void setUp() {
      priceServiceImpl = new PriceServiceImpl(priceRepository);
      Price price = Mockito.mock(Price.class);
      Price anotherPrice = Mockito.mock(Price.class);
      prices = new ArrayList<>();
      prices.add(price);
      prices.add(anotherPrice);
  }

  @Test
  public void givenValidParamsShouldReturnPriceList() {
      Mockito.when(priceRepository.getPrices(LocalDateTime.parse("2020-06-14T16:00:00"), Long.valueOf(1), Long.valueOf(1)))
          .thenReturn(prices);

    Optional<List<Price>> expectedPrices = priceServiceImpl
        .getPrices(LocalDateTime.parse("2020-06-14T16:00:00"), Long.valueOf(1), Long.valueOf(1));

    assertEquals(expectedPrices, Optional.of(prices));
    assertTrue(prices.size() > 0);

  }

}
