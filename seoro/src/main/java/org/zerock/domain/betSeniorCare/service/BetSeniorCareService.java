package org.zerock.domain.betSeniorCare.service;

import java.util.List;

import org.zerock.domain.betSeniorCare.dto.request.BetSeniorCareRequestDTO;
import org.zerock.domain.betSeniorCare.dto.response.BetSeniorCareResponseCfDTO;
import org.zerock.domain.betSeniorCare.dto.response.BetSeniorCareResponseRsDTO;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.senior.entity.Senior;

public interface BetSeniorCareService {
	// 예약 신청 거는 서비스
	public void createBetSeniorCare(Senior senior, Caregiver caregiver, BetSeniorCareRequestDTO dto) throws Exception;
	
	public void acceptConnect(long conno) throws Exception;
	
	public void declineConnect(long conno, String reason) throws Exception;

	public List<BetSeniorCareResponseRsDTO> getreserveList(long careno) throws Exception;

	public List<BetSeniorCareResponseCfDTO> getconfirmList(long careno, int year) throws Exception;
}
