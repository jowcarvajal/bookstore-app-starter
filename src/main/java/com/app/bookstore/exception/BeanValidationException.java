package com.app.bookstore.exception;

import java.util.ArrayList;
import java.util.List;

public class BeanValidationException extends Exception {

	private List<BeanValidationFieldMessage> errors = new ArrayList<>();
	


	public BeanValidationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BeanValidationException(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
		// TODO Auto-generated constructor stub
	}

	public List<BeanValidationFieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String filedName, String messsage) {
		this.errors.add(new BeanValidationFieldMessage(filedName, messsage));
	}
	
	

}
