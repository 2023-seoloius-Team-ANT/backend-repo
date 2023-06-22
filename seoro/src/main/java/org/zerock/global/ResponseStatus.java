package org.zerock.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
	
	//로그인 공통
	SORC_LOGIN_SUCCESS("ALL000", "로그인 성공"),
	
	// senior(노인) 응답 status
	SENIOR_SIGNUP_SUCCESS("SE000", "노인 회원 생성 성공"),
	SENIOR_DETAIL_SUCCESS("SE001", "노인 세부정보 가져오기 성공"),
	
	//회원가입 승인전 요양사 응답 status
	GET_CAREGIVER_SUCCESS("ADM000", "승인 전 요양사 리스트 가져오기 성공"),
	ACCEPT_CAREGIVER_SUCCESS("ADM001", "요양사 회원 요청 승인 성공"),
	DECLINE_CAREGIVER_SUCCESS("ADM002", "요양사 회원 요청 거절 성공"),
	GET_WORKSETCOUNT_SUCCESS("ADM007", "관리자 잔여 업무 갯수 가져오기 성공"),
	
	//검색내용 저장 성공 status
	SEARCH_SAVE_SUCCESS("SH000", "검색내용 저장 성공"),

  // caregiver(요양사) 응답 status
	CAREGIVER_SIGNUP_SUCCESS("CA000", "요양사 회원 생성 성공"),
	CAREGIVER_INFOUPDATE_SUCCESS("CA001", "요양사 추가 정보 입력 성공"),
	CAREGIVER_INFOBYID_SUCCESS("CA002", "요양사 한명 정보 가져오기 성공"),
	CAREGIVER_GETANS_SUCCESS("CA003", "요양사의 대답 정보 가져오기 성공"),
	CAREGIVER_FILTERLIST_SUCCESS("CA004", "년, 월, 위치 조건에 해당하는 요양사 리스트 가져오기 성공"),
	
	// 노인 -> 요양사 신청 관련 응답 status
	CONNECT_RESEV_SUCCESS("CO000", "노인->요양사 예약 생성 성공"),
	CONNECT_RESEVLIST_SUCCESS("CO001", "요양사 -> 예약 현황 불러오기 성공"),
	CONNECT_CONFIRMLIST_SUCCESS("CO002", "요양사 -> 예약 확정된 노인 리스트 불러오기 성공"),
	CONNECT_ACCEPT_SUCCESS("CO003", "요양사 -> 예약 수락 성공"),
	CONNECT_DECLINE_SUCCESS("CO004", "요양사 -> 예약 거절 성공"),
	
	//신고 관련 응답 status
	COMPLAIN_POST_SUCCESS("COM000", "컴플레인 생성 성공"),
	COMPLAIN_GETALL_SUCCESS("COM001", "모든 컴플레인 리스트 가져오기 성공"),
	COMPLAIN_COMPLETE_SUCCESS("COM002", "컴플레인 확인 완료"),
	
	// 통계 관련 응답 status
	STATISTIC_CAREGIVER_SUCCESS("STA000", "요양사 통계 가져오기 성공"),
	STATISTIC_SENIOR_SUCCESS("ADM003", "년도 월별 신규 노인 회원 가져오가 성공");
	
	
	
	
	
	private final String code;
    private final String message;
}
