package org.zerock.domain.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.admin.dto.request.AdminRequestDTO;
import org.zerock.domain.admin.dto.response.AdminResponseBothDTO;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.admin.dto.response.CaregiverStaticResponseDTO;
import org.zerock.domain.admin.dto.response.WorkStaticResponseDTO;


@Service
public interface AdminService {

	public List<AdminResponseDTO> getCaregiver() throws Exception;
	
	public void acceptCare(Long careno) throws Exception;
	
	public void declineCare(Long careno) throws Exception;
	
	public AdminResponseBothDTO doingLogin(AdminRequestDTO dto) throws Exception;
	
	public CaregiverStaticResponseDTO getStaticCaregiver(int year) throws Exception;
	
	public List<WorkStaticResponseDTO> getadminWork(int year) throws Exception;
}
