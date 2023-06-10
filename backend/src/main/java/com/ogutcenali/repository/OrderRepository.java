package com.ogutcenali.repository;

import com.ogutcenali.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Integer userId);
}
