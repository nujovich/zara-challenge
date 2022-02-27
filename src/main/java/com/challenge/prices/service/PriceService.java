package com.challenge.prices.service;

import com.challenge.prices.entity.Price;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceService {

    Optional<List<Price>> getPrices(LocalDateTime dateTime, Long productId, Long brandId);

}
