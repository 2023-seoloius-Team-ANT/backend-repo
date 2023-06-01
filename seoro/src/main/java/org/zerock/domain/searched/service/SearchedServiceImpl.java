package org.zerock.domain.searched.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.searched.dto.request.SearchedDTO;
import org.zerock.domain.searched.entity.Searched;
import org.zerock.domain.searched.repository.SearchRepo;
import org.zerock.domain.senior.entity.Senior;
import org.zerock.domain.senior.repository.SeniorRepo;

@Service
public class SearchedServiceImpl implements SearchedService {

	@Autowired
	SearchRepo searchRepo;
	
	@Autowired
	SeniorRepo seniorRepo;
	
	@Override
	public void savingSearch(SearchedDTO dto, Long seniorno) {
		Searched searched = new Searched();
		searched = searched.dtoToSearch(dto);
		
		Senior senior = seniorRepo.findById(seniorno).get();
		searched.setSenior(senior);
		
		searchRepo.save(searched);
	}
}
