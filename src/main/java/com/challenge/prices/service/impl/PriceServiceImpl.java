package com.challenge.prices.service.impl;


import com.challenge.prices.entity.Price;
import com.challenge.prices.repository.PriceRepository;
import com.challenge.prices.service.PriceService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {

  private final PriceRepository priceRepository;

  public PriceServiceImpl(PriceRepository priceRepository) {
      this.priceRepository = priceRepository;
  }

  @Override
  public Optional<List<Price>> getPrices(LocalDateTime dateTime, Long productId, Long brandId) {
    return Optional.of(priceRepository.getPrices(dateTime, productId, brandId));
  }
}
