package org.zerock.domain.senior.service;


import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.searched.dto.request.SearchedDTO;
import org.zerock.domain.searched.entity.Searched;
import org.zerock.domain.searched.repository.SearchRepo;
import org.zerock.domain.senior.dto.request.SeniorRequestDTO;
import org.zerock.domain.senior.dto.response.SeniorResponseDTO;
import org.zerock.domain.senior.entity.Senior;
import org.zerock.domain.senior.repository.SeniorRepo;

@Service
public class SeniorServiceImpl implements SeniorService {

	
	@Autowired
	SeniorRepo seniorRepo;
	
	@Autowired
	SearchRepo searchRepo;
	
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
	
	@Override
	public SeniorResponseDTO detailSenior(Long seniorno) {
		Senior senior = new Senior();
		senior = seniorRepo.findById(seniorno).get();
		SeniorResponseDTO dto = senior.responseSeniorDto(senior);
		return dto;
	}
	
	@Override
	public void savingSearch(SearchedDTO dto) {
		Searched searched = new Searched();
		searched = searched.dtoToSearch(dto);
		searchRepo.save(searched);
	}
	
}
