package com.misael.api.payment.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.misael.api.payment.exceptions.UnauthorizedTransactionException;
import com.misael.api.payment.exceptions.UserExistsException;
import com.misael.api.payment.exceptions.UserNotFoundException;
import com.misael.api.payment.exceptions.UserTypeWithoutPermissionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail methodArgumentoNotValid(MethodArgumentNotValidException ex){
		List<String> fieldError = ex.getBindingResult().getFieldErrors().stream()
				.map(FieldError::getDefaultMessage).toList();
		String errorMessage = fieldError.toString();
		return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
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
	
	@ExceptionHandler(UnauthorizedTransactionException.class)
	public ResponseEntity<Object> unauthorizedTransaction(){
		return ResponseEntity.internalServerError().body("Transaction Not Authorized");
	}
	
}
