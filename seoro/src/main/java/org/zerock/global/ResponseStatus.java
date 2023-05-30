package org.zerock.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
	// senior(노인) 응답 status
	SENIOR_SIGNUP_SUCCESS("d", "d"),
	
	// caregiver(요양사) 응답 status
	CAREGIVER_SIGNUP_SUCCESS("ㅇ", "ㅇㄹ"),
	
	
	//회원가입 승인전 요양사 응답 status
	GET_CAREGIVER_SUCCESS("ADM000", "승인 전 요양사 리스트 가져오기 성공");
	
	
//	ResponseStatus(String code,String message) {
//		this.code = code;
//		this.message = message;
//	}
	
	
	private final String code;
    private final String message;
}
