package org.zerock.domain.caregiver.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.request.UpdateInfoCaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;

import lombok.extern.java.Log;

@Service
@Log
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

	@Override
	public void updateInfoCaregiver(UpdateInfoCaregiverRequestDTO dto, long cid) throws Exception {
		Optional<Caregiver> updateCaregiver = caregiverRepo.findById(cid);
		
		updateCaregiver.ifPresent(cg -> {
			cg.setChar1(dto.getChar1());
			cg.setChar2(dto.getChar2());
			cg.setChar3(dto.getChar3());
			cg.setWorkday(Integer.parseInt(dto.getWorkday()));
			cg.setInfo(dto.getInfo());
			cg.setVisitTime(dto.getVisittime());
			cg.setExp(dto.getExp());
			cg.setCertifi(dto.getCertifi());
			cg.setGood(dto.getGood());
			cg.setGoal(dto.getGoal());
			
			caregiverRepo.save(cg);	
		});
	}

	@Override
	public CaregiverResponseDTO getCaregiverById(long careno) throws Exception {
		Caregiver caregiver = caregiverRepo.findById(careno).get();
		CaregiverResponseDTO dto = caregiver.toCaregiverResponseDTO(caregiver);
		
		// 성별 변환 
		if(caregiver.getGender() == 0) { // 0: 남성, 1: 여성
			dto.setGenderStr("남성");		
		} else {
			dto.setGenderStr("여성");		
		}
		
		// 년도 -> 나이로 변환 (만 나이를 기준으로 합니다)
		LocalDate now = LocalDate.now();	
		int year = now.getYear(); // 올해 년도
		String birth = caregiver.getBirth(); // ex) 19981119 형태
		int birthYear = Integer.parseInt(birth.substring(0,4)); // 1998만 뽑아옴
		
		dto.setAge(year - birthYear +1);
		return dto;
		
		
	}

}
