package com.everis.escuela.repository;

import com.everis.escuela.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query(value = "select sum(s.quantity) " +
            "from Stock s " +
            "where s.product_id = ?1 " +
            "group by s.product_id",
            nativeQuery = true)
    Long findByProductId(Long productId);

}
