package org.zerock.domain.betSeniorCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.betSeniorCare.dto.request.BetSeniorCareRequestDTO;
import org.zerock.domain.betSeniorCare.dto.response.BetSeniorCareResponseCfDTO;
import org.zerock.domain.betSeniorCare.dto.response.BetSeniorCareResponseRsDTO;
import org.zerock.domain.betSeniorCare.service.BetSeniorCareService;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;
import org.zerock.domain.senior.entity.Senior;
import org.zerock.domain.senior.repository.SeniorRepo;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/connect")
@Log
public class BetSeniorCareController {
	
	@Autowired
	private BetSeniorCareService connectService;
	
	@Autowired
	private SeniorRepo seniorRepo;
	
	@Autowired
	private CaregiverRepo caregiverRepo;

	// 노인이 요양사에게 신청 거는 API
	@PostMapping
	public ResponseEntity<ResponseFormat<BetSeniorCareRequestDTO>> createConnect(BetSeniorCareRequestDTO dto) throws Exception{
		Caregiver caregiver = caregiverRepo.findByCareno(dto.getCareno());
		Senior senior = seniorRepo.findBySeniorno(dto.getSeniorno());
		connectService.createBetSeniorCare(senior, caregiver, dto);
		ResponseFormat<BetSeniorCareRequestDTO> responseFormat = new ResponseFormat<>(ResponseStatus.CONNECT_RESEV_SUCCESS);
	    return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	// 요양사 - 신청현황 리스트 불러오는 API
	@GetMapping("/{careno}")
	public ResponseEntity<ResponseFormat<List<BetSeniorCareResponseRsDTO>>> getreserveList(@PathVariable long careno) throws Exception{
		List<BetSeniorCareResponseRsDTO> betSeniorCareResponseRsDTO =connectService.getreserveList(careno);
		ResponseFormat<List<BetSeniorCareResponseRsDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.CONNECT_RESEVLIST_SUCCESS, betSeniorCareResponseRsDTO);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 예약 확정된 노인 리스트 불러오는 API
	@GetMapping("/confirm/{careno}/{year}")
	public ResponseEntity<ResponseFormat<List<BetSeniorCareResponseCfDTO>>> getconfirmList(@PathVariable long careno, @PathVariable int year) throws Exception{
		List<BetSeniorCareResponseCfDTO> betSeniorCareResponseRsDTO =connectService.getconfirmList(careno, year);
		ResponseFormat<List<BetSeniorCareResponseCfDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.CONNECT_CONFIRMLIST_SUCCESS, betSeniorCareResponseRsDTO);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 신청 수락하는 API
	@PutMapping("/{conno}/accept")
	public ResponseEntity<ResponseFormat<Long>> acceptConnect(@PathVariable long conno) throws Exception{
		connectService.acceptConnect(conno);
		ResponseFormat<Long> responseFormat = new ResponseFormat<>(ResponseStatus.CONNECT_ACCEPT_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 신청 거절하는 API
	@PutMapping("/{conno}/decline")
	public ResponseEntity<ResponseFormat<Long>> declineConnect(@PathVariable long conno, @RequestParam(required = false) String reason) throws Exception{
		connectService.declineConnect(conno, reason);
		ResponseFormat<Long> responseFormat = new ResponseFormat<>(ResponseStatus.CONNECT_DECLINE_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}

}
