
package com.app.bookstore.controller;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.bookstore.exception.BeanValidationException;
import com.app.bookstore.exception.DataIntegrityViolationException;
import com.app.bookstore.exception.Exception;
import com.app.bookstore.exception.ObjectNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Exception> objectNotFoundException (ObjectNotFoundException exception, ServletRequest request) {
		Exception error = new Exception(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Exception> dataIntegrityViolationException (DataIntegrityViolationException exception, ServletRequest request) {
		Exception error = new Exception(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Exception> validatorErrors (org.springframework.web.bind.MethodArgumentNotValidException exception, ServletRequest request) {
		BeanValidationException error = new BeanValidationException(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na validacao dos campos");
		for(FieldError fieldError : exception.getBindingResult().getFieldErrors() ) {
			error.addErrors(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
