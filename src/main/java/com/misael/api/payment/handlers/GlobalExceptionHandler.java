package com.misael.api.payment.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.misael.api.payment.exceptions.UserExistsException;
import com.misael.api.payment.exceptions.UserNotFoundException;
import com.misael.api.payment.exceptions.UserTypeWithoutPermissionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> validatorException(MethodArgumentNotValidException ex){
		List<String> errors = ex.getBindingResult().getFieldErrors()
				.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		return new ResponseEntity<Map<String, List<String>>>(getErrorsMap(errors),new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}	
	
	private Map<String, List<String>> getErrorsMap(List<String> errors){
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return errorResponse;
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> userNotFound(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
	} 
	
	@ExceptionHandler(UserTypeWithoutPermissionException.class)
	public ResponseEntity<Object> userTypeWithoutPermission(){
		return ResponseEntity.status(HttpStatus.CONFLICT).body("User Type Without Permission, value not transferred");
	}
	
	@ExceptionHandler(UserExistsException.class)
	public ResponseEntity<Object> userExists(){
		return ResponseEntity.internalServerError().body("User Exists");
	}
}
