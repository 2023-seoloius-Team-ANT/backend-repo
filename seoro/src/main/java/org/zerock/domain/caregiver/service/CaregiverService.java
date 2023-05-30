package org.zerock.domain.caregiver.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;

@Service
public interface CaregiverService {
	public void createCaregiver(CaregiverRequestDTO DTO) throws Exception;

}
