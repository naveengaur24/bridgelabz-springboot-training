package com.example.shopsmart.repository;

import com.example.shopsmart.entity.Order;
import com.example.shopsmart.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // customer ke saare orders
    List<Order> findByCustomerId(Long customerId);

    // pagination ke saath — page 1, page 2 etc.
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

    // status se filter —> pendinggg, delivered etc....
    List<Order> findByStatus(OrderStatus status);

    // date range mein orders
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :start AND :end")
    List<Order> findByDateRange(@Param("start") LocalDate start,
                                @Param("end") LocalDate end);
}