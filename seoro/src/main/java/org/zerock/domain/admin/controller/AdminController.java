package org.zerock.domain.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.admin.service.AdminService;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;


@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/caregiver")
	public ResponseEntity<ResponseFormat<List<Caregiver>>> getCaregiver(Integer regCheck) throws Exception {
		
		List<Caregiver> allCaregivers = adminService.getCaregiver(regCheck);
		ResponseFormat<List<Caregiver>> responseFormat = new ResponseFormat<>(ResponseStatus.CAREGIVER_SIGNUP_SUCCESS, allCaregivers);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
}
