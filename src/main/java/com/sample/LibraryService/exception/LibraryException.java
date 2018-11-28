package com.sample.LibraryService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sample.LibraryService.model.SysMessage;

/**
 * Handels all the exceptions thrown and returns sysMessage
 * 
 * @author Sahana
 *
 */
@ControllerAdvice
@Controller
public class LibraryException {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
		SysMessage message = new SysMessage();
		message.setCode(500);
		message.setStatus("Error");
		message.setMessage(ex.getMessage());
		return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
