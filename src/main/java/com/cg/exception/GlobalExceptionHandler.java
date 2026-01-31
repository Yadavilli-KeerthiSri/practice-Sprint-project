package com.cg.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> resourceNotFound(ResourceNotFound ex,WebRequest request){
		ErrorDetails errordetail=new ErrorDetails(ex.getMessage(),new Date(),request.getDescription(false));
		return new ResponseEntity<> (errordetail,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(ResourceNotFound ex,WebRequest request){
		ErrorDetails errordetail=new ErrorDetails(ex.getMessage(),new Date(),request.getDescription(false));
		return new ResponseEntity<> (errordetail,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
