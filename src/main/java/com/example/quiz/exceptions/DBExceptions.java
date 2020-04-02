package com.example.quiz.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DBExceptions extends Exception {
	
	private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	
	public DBExceptions() {
		super();
	}
	public DBExceptions(String string) {
		super(string);
	}

	public DBExceptions(String string, Exception e) {
		super(string, e);
	}

	public void IDNotFound(String string) {
		LOGGER.log(Level.INFO,string);
	}
}