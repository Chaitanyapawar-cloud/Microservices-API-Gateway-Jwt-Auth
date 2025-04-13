package com.csp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csp.entity.ProductDetails;
import com.csp.repository.ProductDetailsRepo;

import jakarta.ws.rs.NotFoundException;

@Service
public class ProductServiceImpl {
	
		@Autowired
		private ProductDetailsRepo productDetailsRepo;
		
		
		public String saveProduct(ProductDetails dto){
			productDetailsRepo.save(dto);
			return "Product details saved successfully";
		}
		
		public ProductDetails getProductById(Integer id){
			return productDetailsRepo.findById(id).orElseThrow(()->new NotFoundException());
			
		}
}
