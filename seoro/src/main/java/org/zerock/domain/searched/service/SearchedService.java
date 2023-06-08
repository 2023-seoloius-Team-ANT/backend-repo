package org.zerock.domain.searched.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.searched.dto.request.SearchedDTO;

@Service
public interface SearchedService {

	public void savingSearch(SearchedDTO dto, Long seniorno) throws Exception;
}
