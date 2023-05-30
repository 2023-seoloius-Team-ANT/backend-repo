package org.zerock.domain.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CaregiverRepo caregiverRepo;
	
	@Override
	public List<AdminResponseDTO> getCaregiver() throws Exception {
		List<Caregiver> caregivers = caregiverRepo.getWaitCare(0); // 승인대기중인 요양사를 db에서 출력하기
		List<AdminResponseDTO> dtoList = new ArrayList<>();
		
		for(Caregiver caregiver: caregivers) {
			AdminResponseDTO dto = caregiver.puttingDTO(caregiver);
			dtoList.add(dto);
		}
		return dtoList;
		
	}
}
