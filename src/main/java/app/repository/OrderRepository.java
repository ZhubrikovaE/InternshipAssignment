package app.repository;

import app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    List<Order> findBySeccodeAndTypeAndStatus(String seccode, String type, String status);

    // Максимальная цена покупки (Bid) для инструмента
    @Query("SELECT MAX(o.price) FROM Order o WHERE o.seccode = :seccode AND o.type = 'BUY' AND o.status = 'PENDING' AND o.quantity > 0")
    Double findMaxBidPriceBySeccode(@Param("seccode") String seccode);
    
    // Минимальная цена продажи (Ask) для инструмента
    @Query("SELECT MIN(o.price) FROM Order o WHERE o.seccode = :seccode AND o.type = 'SELL' AND o.status = 'PENDING' AND o.quantity > 0")
    Double findMinAskPriceBySeccode(@Param("seccode") String seccode);
}