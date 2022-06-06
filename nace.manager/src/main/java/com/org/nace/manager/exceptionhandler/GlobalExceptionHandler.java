package com.org.nace.manager.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;

import com.org.nace.manager.exceptions.ErrorResponse;
import com.org.nace.manager.exceptions.NaceDetailsFileException;
import com.org.nace.manager.exceptions.RecordAlreadyExistsException;
import com.org.nace.manager.exceptions.RecordNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	
		@ExceptionHandler(value
	            = RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponse
	handleNotFoundException(RecordNotFoundException ex)
	{
	return new ErrorResponse(
	  HttpStatus.NOT_FOUND.value(), ex.getMessage() + " " + ex.getRecordTye());
	}
		
		@ExceptionHandler(value
	            = RecordAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody ErrorResponse
	handleSqlConstraintException(RecordAlreadyExistsException ex)
	{
	return new ErrorResponse(
	  HttpStatus.CONFLICT.value(), ex.getMessage() +  " " + ex.getRecordTye());
	}	
		
		@ExceptionHandler(value
	            = NaceDetailsFileException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse
	handleNaceDetailsFileException(NaceDetailsFileException ex)
	{
	return new ErrorResponse(
	  HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}	
		
	
}
