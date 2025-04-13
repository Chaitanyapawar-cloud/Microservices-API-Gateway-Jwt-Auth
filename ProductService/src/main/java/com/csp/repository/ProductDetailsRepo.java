package com.csp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csp.entity.ProductDetails;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Integer> {

}
