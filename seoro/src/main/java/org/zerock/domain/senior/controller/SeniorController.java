package org.zerock.domain.senior.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	
	
}
