package com.csp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csp.entity.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

}
