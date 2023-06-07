package org.zerock.domain.caregiver.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.request.UpdateInfoCaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.response.CaregiverQueResponseDTO;
import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;

@Service
public interface CaregiverService {
	public long createCaregiver(CaregiverRequestDTO dto, MultipartFile profile, MultipartFile certiImage) throws Exception;
	
	public CaregiverResponseDTO getCaregiverById(long careno) throws Exception;
	
	public void updateInfoCaregiver(UpdateInfoCaregiverRequestDTO dto, long careno) throws Exception;
	
	public CaregiverQueResponseDTO getCaregiverAns(long careno, int queno) throws Exception;
	
	public List<CaregiverResponseDTO> getCaregiverList(int year, int month, BigDecimal lon, BigDecimal lat) throws Exception;

}
