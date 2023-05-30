package org.zerock.domain.caregiver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;
import org.zerock.domain.caregiver.service.CaregiverService;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/caregiver")
@Log
public class CaregiverController {
	
	@Autowired
	private CaregiverService caregiverService;
	
	// 요양사 회원가입 API
	@PostMapping
	public ResponseEntity<ResponseFormat<CaregiverResponseDTO>> createCaregiver(CaregiverRequestDTO dto) throws Exception{
		// aws bucket 추가되면 다시 실행
		//@RequestPart("profileImage") MultipartFile profileImage, @RequestPart("certiImage") MultipartFile certiImage)
		/*
		 * if(profileImage == null || certiImage == null) { log.info("파일이 존재하지 않습니다.");
		 * }
		 */
		caregiverService.createCaregiver(dto);
		ResponseFormat<CaregiverResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.CAREGIVER_SIGNUP_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
		
	}
}
