package org.zerock.domain.complain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.caregiver.dto.response.CarnoResponseDTO;
import org.zerock.domain.complain.dto.request.ComplainRequestDTO;
import org.zerock.domain.complain.dto.response.ComplainResponseDTO;
import org.zerock.domain.complain.service.ComplainService;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;


import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/complain")
@Log
public class ComplainController {
	@Autowired
	private ComplainService complainService;
	
	// 신고 접수 api
	@PostMapping
	public ResponseEntity<ResponseFormat<ComplainResponseDTO>> createComplian(ComplainRequestDTO dto) throws Exception{
		complainService.createComplain(dto);
		
		ResponseFormat<ComplainResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.COMPLAIN_POST_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	// 모든 신고 내용 가져오는 API
	@GetMapping
	public ResponseEntity<ResponseFormat<List<ComplainResponseDTO>>> getComplainList() throws Exception{
		List<ComplainResponseDTO> complainResponseDTO = complainService.getComplainList();
		ResponseFormat<List<ComplainResponseDTO>> responseFormat= new ResponseFormat<>(ResponseStatus.COMPLAIN_GETALL_SUCCESS, complainResponseDTO);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 신고 확인으로 바꾸는 API
	@PutMapping("/{complainno}")
	public ResponseEntity<ResponseFormat<Long>> checkComplain(@PathVariable long complainno) throws Exception{
		complainService.checkComplain(complainno);
		ResponseFormat<Long> responseFormat = new ResponseFormat<>(ResponseStatus.COMPLAIN_COMPLETE_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	
}
