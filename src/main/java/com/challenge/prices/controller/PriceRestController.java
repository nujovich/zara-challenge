package com.challenge.prices.controller;

import com.challenge.prices.entity.Price;
import com.challenge.prices.service.PriceService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenge/v1")
public class PriceRestController {

    private final PriceService priceService;

    public PriceRestController(PriceService priceService) {
        this.priceService = priceService;
    }


    @GetMapping("/price/{dateTime}/{productId}/{brandId}")
    public ResponseEntity getPrice(@PathVariable("dateTime") @DateTimeFormat(iso = ISO.DATE_TIME)
        String dateTime, @PathVariable("productId") Long productId, @PathVariable("brandId") Long brandId) {
        Optional<Price> price = Optional.empty();
        Optional<List<Price>> prices = priceService.getPrices(LocalDateTime.parse(dateTime), productId, brandId);

        if(prices.isPresent()) {
            price = prices.get().stream().findFirst();
        }

        return price.isPresent() ?
            ResponseEntity.ok(price.get()) :
            ResponseEntity.notFound().build();
    }

}
