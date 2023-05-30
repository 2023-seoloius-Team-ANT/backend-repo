package org.zerock.domain.caregiver.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.request.UpdateInfoCaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;

@Service
public interface CaregiverService {
	public void createCaregiver(CaregiverRequestDTO dto) throws Exception;
	
	public CaregiverResponseDTO getCaregiverById(long careno) throws Exception;
	
	public void updateInfoCaregiver(UpdateInfoCaregiverRequestDTO dto, long careno) throws Exception;

}
