package com.pbx.tabuk.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AddressControllerAdvice {

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ErrorDetails> addressNotFoundExceptionHandler( Exception ex,
			WebRequest request ) {
		return ResponseEntity.status( HttpStatus.NOT_FOUND )
				.body( new ErrorDetails( LocalDateTime.now(), ex.getMessage(),
						request.getDescription( false ) ) );
	}
}
