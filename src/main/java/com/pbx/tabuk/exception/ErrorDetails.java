package com.pbx.tabuk.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String description;
}
