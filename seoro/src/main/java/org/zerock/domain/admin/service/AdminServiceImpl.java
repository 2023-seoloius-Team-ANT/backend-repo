package org.zerock.domain.admin.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.admin.Login.LoginFailedException;
import org.zerock.domain.admin.Login.RejectedException;
import org.zerock.domain.admin.dto.request.AdminRequestDTO;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;
import org.zerock.domain.senior.repository.SeniorRepo;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CaregiverRepo caregiverRepo;
	
	@Autowired
	private SeniorRepo seniorRepo;
	
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
	public void acceptCare(Long careno) throws Exception {
		int acceptCount = caregiverRepo.changeCare(careno, 1); //1은 승인을 의미
		log.info("update count: " + acceptCount);
	}
	
	@Override //요양사 거절 API
	public void declineCare(Long careno) throws Exception {
		int declineCount = caregiverRepo.changeCare(careno, 2); //2는 거절을 의미
		log.info("update count: " + declineCount);
	}
	
	@Override
	public void doingLogin(AdminRequestDTO dto) throws Exception {	
		int senior = seniorRepo.findBySid(dto.getSid(), dto.getSpwd());	
		int caregiver = caregiverRepo.findByCid(dto.getCid(), dto.getCpwd());
		
		Caregiver cReg = caregiverRepo.findByCreg(dto.getCid(), dto.getCpwd());
		
		if(senior == 0 && caregiver == 0) {
			throw new LoginFailedException("아이디 혹은 비밀번호가 잘못되었습니다."); //로그인 실패시 강제예외 발생
		} else if(cReg.getRegCheck() == 2) {
			throw new RejectedException("승인이 거절된 회원이므로 로그인 실패"); 
		} else {
			log.info("로그인 성공");
		}
			
	}
}
