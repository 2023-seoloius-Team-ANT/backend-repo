package org.zerock.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
	// senior(노인) 응답 status
	SENIOR_SIGNUP_SUCCESS("d", "d"),
	
	// caregiver(요양사) 응답 status
	CAREGIVER_SIGNUP_SUCCESS("ㅇ", "ㅇㄹ");
	
	
//	ResponseStatus(String code,String message) {
//		this.code = code;
//		this.message = message;
//	}
	
	
	private final String code;
    private final String message;
}
