package org.zerock.domain.complain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.complain.dto.request.ComplainRequestDTO;
import org.zerock.domain.complain.dto.response.ComplainResponseDTO;

@Service
public interface ComplainService {

	void createComplain(ComplainRequestDTO dto) throws Exception;
	
	public List<ComplainResponseDTO> getComplainList() throws Exception;
	
	public void checkComplain(long complainno) throws Exception;
}
