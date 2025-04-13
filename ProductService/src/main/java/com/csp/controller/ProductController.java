package com.csp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csp.entity.ProductDetails;
import com.csp.service.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ProductDetails> getProductById(@PathVariable("id") Integer id){
		ProductDetails details = productServiceImpl.getProductById(id);
		return new ResponseEntity<ProductDetails>(details,HttpStatus.OK);
	}
	
	@PostMapping("/saveDetails")
	public ResponseEntity<String> getProductById(@RequestBody ProductDetails dto){
		String msg = productServiceImpl.saveProduct(dto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

}
