package com.csp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Integer custId;
	private Integer prodId;
	private String status;
	private Integer quantity;
}
