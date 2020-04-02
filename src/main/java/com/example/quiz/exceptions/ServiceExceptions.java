package com.example.quiz.exceptions;

import org.hibernate.service.spi.ServiceException;

public class ServiceExceptions extends ServiceException {

	public ServiceExceptions(String string) {
		super(string);
	}
	public ServiceExceptions(String detailMessage, Exception cause) {
		super(detailMessage, cause);
	}
}