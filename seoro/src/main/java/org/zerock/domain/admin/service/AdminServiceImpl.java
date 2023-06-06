package org.zerock.domain.admin.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.admin.Login.LoginFailedException;
import org.zerock.domain.admin.Login.RejectedException;
import org.zerock.domain.admin.dto.request.AdminRequestDTO;
import org.zerock.domain.admin.dto.response.AdminResponseBothDTO;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;
import org.zerock.domain.senior.entity.Senior;
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
	public AdminResponseBothDTO doingLogin(AdminRequestDTO dto) throws Exception {	//로그인(공통)
		int seniorCount = seniorRepo.findBySid(dto.getSid(), dto.getSpwd());	
		int caregiverCount = caregiverRepo.findByCid(dto.getCid(), dto.getCpwd());
		
		Caregiver cReg = caregiverRepo.findByCaregiver(dto.getCid(), dto.getCpwd());
		
		if(seniorCount == 0 && caregiverCount == 0) {
			throw new LoginFailedException("아이디 혹은 비밀번호가 잘못되었습니다."); //로그인 실패시 강제예외 발생
			
		} else if(seniorCount == 1) { //노인이 로그인 성공했을때
			log.info("노인 로그인 성공");
			Senior senior = seniorRepo.findBySenior(dto.getSid(), dto.getSpwd());
			AdminResponseBothDTO responseBoth = senior.responseBothSeni(senior);
			return responseBoth;
			
		} else if(cReg.getRegCheck() == 2) { // 승인이 거절된 요양사의 경우 강제예외 발생
			throw new RejectedException("승인이 거절된 회원이므로 로그인 실패"); 
			
		} else if(caregiverCount == 1) { //요양사가 로그인 성공했을때
			log.info("요양사 로그인 성공");
			Caregiver caregiver = caregiverRepo.findByCaregiver(dto.getCid(), dto.getCpwd());
			AdminResponseBothDTO responseBoth = caregiver.responseBothCare(caregiver);
			return responseBoth;	
			
		}
		
		return null;
	}
}
