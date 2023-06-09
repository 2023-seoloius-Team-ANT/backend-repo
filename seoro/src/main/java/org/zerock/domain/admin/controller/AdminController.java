package org.zerock.domain.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.admin.dto.request.AdminRequestDTO;
import org.zerock.domain.admin.dto.response.AdminResponseBothDTO;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.admin.dto.response.CaregiverStaticResponseDTO;
import org.zerock.domain.admin.dto.response.ComplainCaregiverCntDTO;
import org.zerock.domain.admin.dto.response.MatchingStaticMonthResponseDTO;
import org.zerock.domain.admin.dto.response.SeniorStaticResponseDTO;
import org.zerock.domain.admin.dto.response.WorkStaticResponseDTO;
import org.zerock.domain.admin.service.AdminService;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;

import lombok.extern.java.Log;


@RestController
@RequestMapping("/api/v1")
@Log
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/caregiver") //회원가입 승인 전 요양사 리스트 출력
	public ResponseEntity<ResponseFormat<List<AdminResponseDTO>>> getCaregiver() throws Exception {
		
		List<AdminResponseDTO> dtoList = adminService.getCaregiver();
		ResponseFormat<List<AdminResponseDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.GET_CAREGIVER_SUCCESS, dtoList);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	@GetMapping("/admin/work")
	public ResponseEntity<ResponseFormat<ComplainCaregiverCntDTO>> findCompCareCnt() throws Exception {
		//GET_WORKSETCOUNT_SUCCESS
		System.out.println("test");
		ComplainCaregiverCntDTO dto = adminService.findCompCareCnt();
		ResponseFormat<ComplainCaregiverCntDTO> responseFormat = new ResponseFormat<>(ResponseStatus.GET_WORKSETCOUNT_SUCCESS, dto);
		System.out.println(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	@PutMapping("/admin/{careno}/accept") //관리자가 회원가입 승인버튼을 눌렀을때
	public ResponseEntity<ResponseFormat<ResponseStatus>> acceptCare(@PathVariable Long careno) throws Exception {
		adminService.acceptCare(careno);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.ACCEPT_CAREGIVER_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	@PutMapping("/admin/{careno}/decline") //관리자가 회원가입 거절버튼 눌렀을때
	public ResponseEntity<ResponseFormat<ResponseStatus>> declineCare(@PathVariable Long careno) throws Exception {
		adminService.declineCare(careno);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.DECLINE_CAREGIVER_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	@PostMapping("/login") //로그인(공통) API
	public ResponseEntity<ResponseFormat<AdminResponseBothDTO>> doingLogin(@RequestBody AdminRequestDTO dto) throws Exception {
		AdminResponseBothDTO responseBoth = adminService.doingLogin(dto);
		ResponseFormat<AdminResponseBothDTO> responseFormat = new ResponseFormat<>(ResponseStatus.SORC_LOGIN_SUCCESS, responseBoth);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	@GetMapping("/admin/caregiver/{year}") // 년도 월별 신규 요양사 회원 가져오는 API  
	public ResponseEntity<ResponseFormat<CaregiverStaticResponseDTO>> getStaticCaregiver(@PathVariable int year) throws Exception{
		CaregiverStaticResponseDTO caregiverResponse = adminService.getStaticCaregiver(year);
		ResponseFormat<CaregiverStaticResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.ADMIN_STATISTICS_NEWCAREGIVER_SUCCESS, caregiverResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	@GetMapping("/admin/seinor/{year}")
	public ResponseEntity<ResponseFormat<SeniorStaticResponseDTO>> getStaticSenior(@PathVariable int year) throws Exception{
		System.out.println("thiiscall");
		SeniorStaticResponseDTO seniorResponse = adminService.getStaticSenior(year);
		ResponseFormat<SeniorStaticResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.STATISTIC_SENIOR_SUCCESS, seniorResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
  
	@GetMapping("/admin/connect/{year}")
	public ResponseEntity<ResponseFormat<List<WorkStaticResponseDTO>>> getadminWork(@PathVariable int year) throws Exception{
		List<WorkStaticResponseDTO> workResponse = adminService.getadminWork(year);
		ResponseFormat<List<WorkStaticResponseDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.ADMIN_STATISTICS_WORK_SUCCESS, workResponse);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	@GetMapping("/admin/connect")
	public ResponseEntity<ResponseFormat<MatchingStaticMonthResponseDTO>> getMatchingStatic() throws Exception{
		MatchingStaticMonthResponseDTO matchingResponse = adminService.getMatchingStatic();
		ResponseFormat<MatchingStaticMonthResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.ADMIN_STATISTICS_MATCHING_CNT_SUCCESS, matchingResponse);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	

}
