package org.zerock.domain.senior.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.searched.dto.request.SearchedDTO;
import org.zerock.domain.senior.dto.request.SeniorRequestDTO;
import org.zerock.domain.senior.dto.response.SeniorResponseDTO;
import org.zerock.domain.senior.service.SeniorService;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;

@RestController
@RequestMapping("/api/v1")
public class SeniorController {
	
	@Autowired
	SeniorService seniorService;
	
	@PostMapping("/senior") //노인회원가입 등록 API
	public ResponseEntity<ResponseFormat<ResponseStatus>> regSenior(SeniorRequestDTO dto) throws Exception {
		seniorService.regSenior(dto);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.SENIOR_SIGNUP_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	@GetMapping("/senior/{seniorno}") //노인 세부 정보 불러오는 API
	public ResponseEntity<ResponseFormat<SeniorResponseDTO>> detailSenior(@PathVariable Long seniorno) throws Exception {
		SeniorResponseDTO seniorDto = seniorService.detailSenior(seniorno);
		ResponseFormat<SeniorResponseDTO> responseFormat = new ResponseFormat<SeniorResponseDTO>(ResponseStatus.SENIOR_DETAIL_SUCCESS, seniorDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	@PostMapping("/search") //위치검색시 검색 기록 저장하는 API
	public ResponseEntity<ResponseFormat<ResponseStatus>> savingSearch(SearchedDTO dto, HttpServletRequest req) throws Exception {
		//클라이언트로부터 검색한주소, 검색한주소의 위도, 및 경도 이 3가지를 받아온다고 생각하자.
		//현재 로그인중인 노인의 pk 고려할것!!! 여기에 세션 추가!
//		 // 세션생성
//	    HttpSession session = request.getSession();
//	    HttpSession session1 = request.getSession(true);
//	    HttpSession session2 = request.getSession(false);
//	    // 새로운세션 생성여부
//	    boolean sNew = session.isNew();
//	    // 세션 유지시간 설정(초단위로)
//	    // 60*60 = 1시간
//	    int sTime = 60*60; 
//	    session.setMaxInactiveInterval(sTime);
//	    // 무한대설정
//	    session.setMaxInactiveInterval(-1);
//	    // 세션Id 값 가져오기
//	    String sId = session.getId();
//	    String hello = "Hello session!!";
//	    // 세션에 값 저장하기
//	    session.setAttribute("session_hello", hello);
//	    // 세션에서 값 가져오기
//	    String word = (String) session.getAttribute("session_hello"); 
	
		seniorService.savingSearch(dto);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.SEARCH_SAVE_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);	
	}
}
