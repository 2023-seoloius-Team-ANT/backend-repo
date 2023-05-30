package org.zerock.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
	// senior(노인) 응답 status
	SENIOR_SIGNUP_SUCCESS("d", "d"),
	
	// caregiver(요양사) 응답 status
	CAREGIVER_SIGNUP_SUCCESS("CA000", "요양사 회원 생성 성공"),
	CAREGIVER_INFOUPDATE_SUCCESS("CA001", "요양사 추가 정보 입력 성공"),
	CAREGIVER_INFOBYID_SUCCESS("CA002", "요양사 한명 정보 가져오기 성공");
	
//	ResponseStatus(String code,String message) {
//		this.code = code;
//		this.message = message;
//	}
	
	
	private final String code;
    private final String message;
}
