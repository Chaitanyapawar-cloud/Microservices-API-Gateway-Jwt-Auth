package com.csp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.csp.dto.OrderDTO;
import com.csp.entity.OrderDetails;
import com.csp.entity.ProductDetails;
import com.csp.feign.ProductService;
import com.csp.repository.OrderDetailsRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.ws.rs.NotFoundException;

@Service
public class OrderServiceImpl {
	
		@Autowired
		private OrderDetailsRepo orderDetailsRepo;
		
		@Autowired
		private ProductService productService;
		
		@CircuitBreaker(name ="OrderServiceCB", fallbackMethod = "fallBackMethod")
		public String placeOrder(OrderDTO dto){
			OrderDetails details = new OrderDetails();
			details.setCustId(dto.getCustId());
			details.setProdId(dto.getProdId());
			details.setQuantity(dto.getQuantity());
			details.setStatus("Placed");
			
			ResponseEntity<ProductDetails> prodDet = productService.getProductById(dto.getProdId());
			ProductDetails det = prodDet.getBody();
			details.setTotalPrice(dto.getQuantity() * det.getPrice());
			
			orderDetailsRepo.save(details);
			return "Order placed successfully";
		}
		
		public String fallBackMethod(OrderDTO dto, Throwable t) {
			return "Unable to place order try after sometime";
		}
		
		
		public OrderDetails getOrderStatus(Integer id) {
			return orderDetailsRepo.findById(id).orElseThrow(()->new NotFoundException());
		}
		
		public OrderDetails changeOrderStatus(Integer id, String status) {
			 Optional<OrderDetails> od = orderDetailsRepo.findById(id);
			 OrderDetails orderDetails;
			 if(od.isPresent()) {
				 orderDetails = od.get();
				 orderDetails.setStatus(status);
				 orderDetailsRepo.save(orderDetails);
			 }else {
				throw new NotFoundException();
			}
			return orderDetails;
		}
		
		
}
