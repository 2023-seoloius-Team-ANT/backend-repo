package org.zerock.domain.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.admin.service.AdminService;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;

import lombok.extern.java.Log;


@RestController
@RequestMapping("/api/v1/admin")
@Log
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/caregiver") //회원가입 승인 전 요양사 리스트 출력
	public ResponseEntity<ResponseFormat<List<AdminResponseDTO>>> getCaregiver() throws Exception {
		
		List<AdminResponseDTO> dtoList = adminService.getCaregiver();
		ResponseFormat<List<AdminResponseDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.GET_CAREGIVER_SUCCESS, dtoList);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	@PutMapping("/{careno}/accept") //관리자가 회원가입 승인버튼을 눌렀을때
	public ResponseEntity<ResponseFormat<ResponseStatus>> acceptCare(@PathVariable Long careno) throws Exception {
		adminService.acceptCare(careno);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.ACCEPT_CAREGIVER_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	@PutMapping("/{careno}/decline") //관리자가 회원가입 거절버튼 눌렀을때
	public ResponseEntity<ResponseFormat<ResponseStatus>> declineCare(@PathVariable Long careno) throws Exception {
		adminService.declineCare(careno);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.DECLINE_CAREGIVER_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
}
