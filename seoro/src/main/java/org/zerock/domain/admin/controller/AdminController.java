package org.zerock.domain.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/caregiver")
	public ResponseEntity<ResponseFormat<List<AdminResponseDTO>>> getCaregiver() throws Exception {
		
		List<AdminResponseDTO> dtoList = adminService.getCaregiver();
		ResponseFormat<List<AdminResponseDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.CAREGIVER_SIGNUP_SUCCESS, dtoList);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
}
