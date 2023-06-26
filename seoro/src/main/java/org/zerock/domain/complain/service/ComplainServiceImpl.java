package org.zerock.domain.complain.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.caregiver.repository.CaregiverRepo;
import org.zerock.domain.complain.dto.request.ComplainRequestDTO;
import org.zerock.domain.complain.dto.response.ComplainResponseDTO;
import org.zerock.domain.complain.entity.Complain;
import org.zerock.domain.complain.repository.ComplainRepository;

import lombok.extern.java.Log;

@Service
@Log
public class ComplainServiceImpl implements ComplainService{
	@Autowired
	private ComplainRepository complainRepo;
	
	@Autowired
	private CaregiverRepo caregiverRepo;
	
	@Override
	public void createComplain(ComplainRequestDTO dto) throws Exception{
		Complain saveComplain = dto.toComplainEntity(dto);
		saveComplain.setCaregiver(caregiverRepo.findByCareno(dto.getCareno()));
		
		complainRepo.save(saveComplain);
	}

	@Override
	public List<ComplainResponseDTO> getComplainList() throws Exception {
		Complain complains = new Complain();
		List<Complain> complainLists = complainRepo.getWaitComplainList();
		List<ComplainResponseDTO> dtos = new ArrayList<>();
		
		for(Complain complainEle: complainLists) {
			ComplainResponseDTO dto = new ComplainResponseDTO();
			dto.setComplainno(complainEle.getComplainno());
			dto.setComplainUser(complainEle.getCaregiver().getCid());
			dto.setContent(complainEle.getContent());
			// localdatetime -> string "2023-11-19" 타입으로 변환해서 저장
			dto.setDate(complainEle.getRegdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void checkComplain(long complainno) throws Exception {
		Optional<Complain> updateComplain = complainRepo.findById(complainno);
		
		updateComplain.ifPresent(com -> {
			com.setStateck(1);
			complainRepo.save(com);
		});
	}
	

}
