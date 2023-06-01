package org.zerock.domain.searched.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.searched.dto.request.SearchedDTO;
import org.zerock.domain.searched.entity.Searched;
import org.zerock.domain.searched.repository.SearchRepo;

@Service
public class SearchedServiceImpl implements SearchedService {

	@Autowired
	SearchRepo searchRepo;
	
	@Override
	public void savingSearch(SearchedDTO dto) {
		Searched searched = new Searched();
		searched = searched.dtoToSearch(dto);
		searchRepo.save(searched);
	}
}
