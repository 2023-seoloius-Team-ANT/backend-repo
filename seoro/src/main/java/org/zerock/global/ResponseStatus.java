package org.zerock.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
	// senior(노인) 응답 status
	SENIOR_SIGNUP_SUCCESS("SE000", "노인 회원 생성 성공"),
	
	// caregiver(요양사) 응답 status
	CAREGIVER_SIGNUP_SUCCESS("ㅇ", "ㅇㄹ"),
	
	
	//회원가입 승인전 요양사 응답 status
	GET_CAREGIVER_SUCCESS("ADM000", "승인 전 요양사 리스트 가져오기 성공"),
	ACCEPT_CAREGIVER_SUCCESS("ADM001", "요양사 회원 요청 승인 성공"),
	DECLINE_CAREGIVER_SUCCESS("ADM002", "요양사 회원 요청 거절 성공");
	
	
//	ResponseStatus(String code,String message) {
//		this.code = code;
//		this.message = message;
//	}
	
	
	private final String code;
    private final String message;
}
