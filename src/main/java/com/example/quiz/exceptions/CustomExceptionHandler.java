package com.example.quiz.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.example.quiz.model.ErrorResponse;
import com.fasterxml.jackson.databind.JsonMappingException;


@ControllerAdvice
public class CustomExceptionHandler {
	
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(BadRequestException.class)
//    public final ResponseEntity<Object> RequestBodyError(BadRequestException ex) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getLocalizedMessage());
//        ErrorResponse error = new ErrorResponse("Request body no satisfied", details);
//        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
//    }
	

	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ExceptionHandler(NoHandlerFoundException.class)
	    public String handle404() {
	        return "exceptions/404page";
	    }
	
	@ExceptionHandler({Exception.class,BadRequestException.class})
    public final ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity constraintError(ConstraintViolationException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Constraint Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleQuizNotFoundException(RecordNotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
	
	  @ExceptionHandler
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  public ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex) {
		  List<String> details = new ArrayList<>();
	       details.add(ex.getLocalizedMessage());
	      ErrorResponse errorResponse = new ErrorResponse("request has empty body",details);
	      return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
 
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        List<String> details = new ArrayList<>();
//        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
//            details.add(error.getDefaultMessage());
//        }
//        ErrorResponse error = new ErrorResponse("Validation Failed", details);
//        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//    }
}