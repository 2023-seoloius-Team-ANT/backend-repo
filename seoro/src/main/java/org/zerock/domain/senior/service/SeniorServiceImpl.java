package org.zerock.domain.senior.service;

import java.time.LocalDateTime;

import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.senior.dto.request.SeniorRequestDTO;
import org.zerock.domain.senior.dto.response.SeniorResponseDTO;
import org.zerock.domain.senior.entity.Senior;
import org.zerock.domain.senior.repository.SeniorRepo;

@Service
public class SeniorServiceImpl implements SeniorService {

	
	@Autowired
	SeniorRepo seniorRepo;
	
	@Override
	public void regSenior(SeniorRequestDTO dto) throws Exception {
		
		Senior senior = new Senior();
		
		if(seniorRepo.existsBySid(dto.getSid())) {
			throw new EntityExistsException();
		} else {
		LocalDateTime time = LocalDateTime.now();	
		senior = dto.seniorForEntity(dto);
		senior.setRegdate(time);
		seniorRepo.save(senior);
		}	
	}
	
	@Override
	public SeniorResponseDTO detailSenior(Long seniorno) throws Exception {
		Senior senior = new Senior();
		senior = seniorRepo.findById(seniorno).get();
		SeniorResponseDTO dto = senior.responseSeniorDto(senior);
		return dto;
	}
	

	
}
