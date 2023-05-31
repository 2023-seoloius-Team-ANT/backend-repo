package org.zerock.domain.senior.service;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.senior.dto.request.SeniorRequestDTO;
import org.zerock.domain.senior.entity.Senior;
import org.zerock.domain.senior.repository.SeniorRepo;

@Service
public class SeniorServiceImpl implements SeniorService {

	
	@Autowired
	SeniorRepo seniorRepo;
	
	@Override
	public void regSenior(SeniorRequestDTO dto) {
		
		Senior senior = new Senior();
		
		if(seniorRepo.existsBySid(dto.getSid())) {
			throw new EntityExistsException();
		} else {
			
		senior = dto.seniorForEntity(dto);
		seniorRepo.save(senior);
		}	
	}
}
