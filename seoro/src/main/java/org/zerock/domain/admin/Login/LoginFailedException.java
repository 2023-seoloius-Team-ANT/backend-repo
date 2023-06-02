package org.zerock.domain.admin.Login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailedException extends RuntimeException {
	public LoginFailedException(String message) {
		super(message);
	}
	
}
