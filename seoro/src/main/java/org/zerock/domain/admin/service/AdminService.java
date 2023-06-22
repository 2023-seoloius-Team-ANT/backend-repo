package org.zerock.domain.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.admin.dto.request.AdminRequestDTO;
import org.zerock.domain.admin.dto.response.AdminResponseBothDTO;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.admin.dto.response.CaregiverStaticResponseDTO;
import org.zerock.domain.admin.dto.response.ComplainCaregiverCntDTO;
import org.zerock.domain.admin.dto.response.SeniorStaticResponseDTO;


@Service
public interface AdminService {

	public List<AdminResponseDTO> getCaregiver() throws Exception;
	
	public void acceptCare(Long careno) throws Exception;
	
	public void declineCare(Long careno) throws Exception;
	
	public AdminResponseBothDTO doingLogin(AdminRequestDTO dto) throws Exception;
	
	public CaregiverStaticResponseDTO getStaticCaregiver(int year) throws Exception;
	
	public ComplainCaregiverCntDTO findCompCareCnt() throws Exception;
	
	public SeniorStaticResponseDTO getStaticSenior(int year) throws Exception;
}
