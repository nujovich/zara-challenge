package com.challenge.prices.repository;

import com.challenge.prices.entity.Price;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Price, Long> {

  @Query("SELECT p FROM TBL_PRICES p"
      + "WHERE ?1 BETWEEN p.START_DATE AND p.END_DATE AND "
      + "p.PRODUCT_ID=?2 AND "
      + "p.BRAND_ID=?3 "
      + "ORDER BY p.PRIORITY ASC")
  List<Price> getPrices(LocalDateTime date, Long productId, Long brandId);

}
