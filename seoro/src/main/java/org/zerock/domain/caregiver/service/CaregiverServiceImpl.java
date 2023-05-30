package org.zerock.domain.caregiver.service;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;

@Service
public class CaregiverServiceImpl implements CaregiverService{
	
	@Autowired
	private CaregiverRepo caregiverRepo;

	@Override
	public void createCaregiver(CaregiverRequestDTO dto) throws Exception {
		// 이미 있는 아이디인지 검증
		if(caregiverRepo.existsByCid(dto.getCid())) { // senior에서도 확인 필요
			throw new EntityExistsException();
		}
		
		Caregiver saveCaregiver = dto.toCaregiverEntity(dto);
		caregiverRepo.save(saveCaregiver);
		
	}

}
