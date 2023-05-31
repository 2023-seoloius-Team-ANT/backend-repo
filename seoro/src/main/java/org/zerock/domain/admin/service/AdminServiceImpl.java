package org.zerock.domain.admin.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CaregiverRepo caregiverRepo;
	
	@Override //회원가입 전 요양사 리스트 불러오기 성공
	public List<AdminResponseDTO> getCaregiver() throws Exception {
		List<Caregiver> caregivers = caregiverRepo.getWaitCare(); // 승인대기중인 요양사를 db에서 출력하기
		List<AdminResponseDTO> dtoList = new ArrayList<>();
		
		for(Caregiver caregiver: caregivers) {
			AdminResponseDTO dto = caregiver.puttingDTO(caregiver);
			dtoList.add(dto);
		}
		return dtoList;
		
	}
	
	@Override //요양사 승인 API
	public void acceptCare(Long careno) {
		int acceptCount = caregiverRepo.changeCare(careno, 1); //1은 승인을 의미
		log.info("update count: " + acceptCount);
	}
	
	@Override //요양사 거절 API
	public void declineCare(Long careno) {
		int declineCount = caregiverRepo.changeCare(careno, 2); //2는 거절을 의미
		log.info("update count: " + declineCount);
	}
}
