package org.zerock.domain.searched.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.searched.dto.request.SearchedDTO;
import org.zerock.domain.searched.service.SearchedService;
import org.zerock.global.ResponseFormat;
import org.zerock.global.ResponseStatus;

@RestController
@RequestMapping("/api/v1")
public class SearchedController {

	@Autowired
	SearchedService searchedService;
	
	@PostMapping("/search/{seniorno}") //위치검색시 검색 기록 저장하는 API
	public ResponseEntity<ResponseFormat<ResponseStatus>> savingSearch(SearchedDTO dto, @PathVariable Long seniorno) throws Exception {
		
		searchedService.savingSearch(dto, seniorno);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.SEARCH_SAVE_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);	
	}
}
