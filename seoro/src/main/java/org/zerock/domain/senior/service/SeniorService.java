package org.zerock.domain.senior.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.senior.dto.request.SeniorRequestDTO;

@Service
public interface SeniorService {
	
	public void regSenior(SeniorRequestDTO dto);
	
}
