package org.zerock.domain.caregiver.controller;

import java.math.BigDecimal;
import java.util.List;

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
import org.zerock.domain.caregiver.dto.response.CaregiverQueResponseDTO;
import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;
import org.zerock.domain.caregiver.service.CaregiverService;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;
import org.zerock.global.aws.S3Service;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/caregiver")
@Log
public class CaregiverController {
	
	@Autowired
	private CaregiverService caregiverService;
	
	// 요양사 회원가입 API
	@PostMapping
	public ResponseEntity<ResponseFormat<CaregiverResponseDTO>> createCaregiver(CaregiverRequestDTO dto, @RequestPart("profileImage") MultipartFile profileImage, @RequestPart("certiImage") MultipartFile certiImage) throws Exception{
		 if(profileImage == null || certiImage == null) { 
			 log.info("파일이 존재하지 않습니다.");
		 }
		 
		 
		caregiverService.createCaregiver(dto, profileImage, certiImage);
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
	
	// 요양사 Q&A에 해당하는 답변 가져오는 API
	@GetMapping("/{careno}/{queno}")
	public ResponseEntity<ResponseFormat<CaregiverQueResponseDTO>> getCaregiverAns(@PathVariable long careno, @PathVariable int queno) throws Exception{
		CaregiverQueResponseDTO caregiverAns = caregiverService.getCaregiverAns(careno, queno);
		ResponseFormat<CaregiverQueResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.CAREGIVER_GETANS_SUCCESS, caregiverAns);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 예약 가능한 요양사 리스트 불러오는 API
	@GetMapping("/{year}/{month}/{lon}/{lat}")
	public ResponseEntity<ResponseFormat<List<CaregiverResponseDTO>>> getCaregiverList(@PathVariable int year, @PathVariable int month, @PathVariable BigDecimal lon, @PathVariable BigDecimal lat) throws Exception{
		List<CaregiverResponseDTO> caregiverResponseDTO =  caregiverService.getCaregiverList(year, month, lon ,lat);
		ResponseFormat<List<CaregiverResponseDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.CAREGIVER_FILTERLIST_SUCCESS, caregiverResponseDTO);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}

	
}
