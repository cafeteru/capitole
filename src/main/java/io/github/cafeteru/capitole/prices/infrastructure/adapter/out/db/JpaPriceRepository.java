package io.github.cafeteru.capitole.prices.infrastructure.adapter.out.db;

import io.github.cafeteru.capitole.prices.application.port.out.PriceRepositoryPort;
import io.github.cafeteru.capitole.prices.infrastructure.adapter.out.db.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long>, PriceRepositoryPort {

    @Query(name = "PriceEntity.getPrice")
    List<PriceEntity> getPrice(
            @Param("dateTime") LocalDateTime dateTime,
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId);
}
