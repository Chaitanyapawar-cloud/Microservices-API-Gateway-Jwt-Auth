package com.csp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csp.dto.OrderDTO;
import com.csp.entity.OrderDetails;
import com.csp.service.OrderServiceImpl;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<String> getProductById(@RequestBody OrderDTO dto){
		String msg = orderServiceImpl.placeOrder(dto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/changeStatus/{id}/{status}")
	public ResponseEntity<OrderDetails> getOrderStatus(@PathVariable("id") Integer id,@PathVariable("status") String status ){
		OrderDetails order = orderServiceImpl.changeOrderStatus(id,status);
		return new ResponseEntity<OrderDetails>(order,HttpStatus.OK);
	}

}
