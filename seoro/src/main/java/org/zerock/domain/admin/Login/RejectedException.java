package org.zerock.domain.admin.Login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RejectedException extends RuntimeException {

	public RejectedException(String message) {
		super(message);
	}
	
}
