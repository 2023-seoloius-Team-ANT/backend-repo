package org.zerock.domain.senior.service;


import org.springframework.stereotype.Service;
import org.zerock.domain.searched.dto.request.SearchedDTO;
import org.zerock.domain.senior.dto.request.SeniorRequestDTO;
import org.zerock.domain.senior.dto.response.SeniorResponseDTO;

@Service
public interface SeniorService {
	
	public void regSenior(SeniorRequestDTO dto);
	
	public SeniorResponseDTO detailSenior(Long seniorno);
	
}
