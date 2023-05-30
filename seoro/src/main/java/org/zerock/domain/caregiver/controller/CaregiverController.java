package org.zerock.domain.caregiver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.request.UpdateInfoCaregiverRequestDTO;
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
	
	// 요양 정보 추가 입력 API
	@PutMapping("/{careno}")
	public ResponseEntity<ResponseFormat<UpdateInfoCaregiverRequestDTO>> updateInfoCaregiver(UpdateInfoCaregiverRequestDTO dto, @PathVariable long careno) throws Exception{
		caregiverService.updateInfoCaregiver(dto, careno);
		ResponseFormat<UpdateInfoCaregiverRequestDTO> responseFormat = new ResponseFormat<>(ResponseStatus.CAREGIVER_INFOUPDATE_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 요양사 세부 정보 불러오는 API
	@GetMapping("/{careno}")
	public ResponseEntity<ResponseFormat<CaregiverResponseDTO>> getCaregiverById(@PathVariable long careno) throws Exception{
		CaregiverResponseDTO caregiver = caregiverService.getCaregiverById(careno);
		ResponseFormat<CaregiverResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.CAREGIVER_INFOBYID_SUCCESS, caregiver);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	
}
