package com.challenge.prices.repository;

import com.challenge.prices.entity.Price;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Price, Long> {

  @Query("SELECT p FROM Price p "
      + "WHERE ?1 BETWEEN startDate AND endDate AND "
      + "productId=?2 AND "
      + "brandId=?3 "
      + "ORDER BY priority ASC")
  List<Price> getPrices(LocalDateTime date, Long productId, Long brandId);

}
