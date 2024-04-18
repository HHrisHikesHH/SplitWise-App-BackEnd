package com.app.DTO;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ResponseDTO {
	
	private boolean success;
	
	private String message;

}
