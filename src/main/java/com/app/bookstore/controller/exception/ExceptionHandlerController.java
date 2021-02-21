
package com.app.bookstore.controller.exception;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.bookstore.exception.ObjectNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ExceptionBaseError> objectNotFoundException (ObjectNotFoundException exception, ServletRequest request) {
		ExceptionBaseError error = new ExceptionBaseError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
