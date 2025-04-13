package com.csp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.csp.entity.ProductDetails;

@FeignClient(name="ProductService",url="http://localhost:8051")
public interface ProductService {
	
	@GetMapping("/product/getById/{id}")
	public ResponseEntity<ProductDetails> getProductById(@PathVariable("id") Integer id);

}
