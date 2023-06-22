package org.zerock.domain.admin.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.admin.Login.LoginFailedException;
import org.zerock.domain.admin.Login.RejectedException;
import org.zerock.domain.admin.dto.request.AdminRequestDTO;
import org.zerock.domain.admin.dto.response.AdminResponseBothDTO;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.admin.dto.response.CaregiverStaticResponseDTO;
import org.zerock.domain.admin.dto.response.WorkStatic;
import org.zerock.domain.admin.dto.response.WorkStaticResponseDTO;
import org.zerock.domain.admin.dto.response.YearMonth;
import org.zerock.domain.admin.dto.response.YearMonthDTO;
import org.zerock.domain.admin.entity.Sadmin;
import org.zerock.domain.admin.repository.AdminRepo;
import org.zerock.domain.betSeniorCare.repository.BetRepo;
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
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private BetRepo betRepo;
	
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
		int adminCount = adminRepo.findByAid(dto.getId(), dto.getPwd());
		int seniorCount = seniorRepo.findBySid(dto.getId(), dto.getPwd());	
		int caregiverCount = caregiverRepo.findByCid(dto.getId(), dto.getPwd());
		
		Caregiver cReg = caregiverRepo.findByCaregiver(dto.getId(), dto.getPwd());
		
		if(seniorCount == 0 && caregiverCount == 0 && adminCount == 0) {
			throw new LoginFailedException("아이디 혹은 비밀번호가 잘못되었습니다."); //로그인 실패시 강제예외 발생
			
		} else if (adminCount == 1) { //관리자가 로그인했을때
			Sadmin sadmin = adminRepo.findByAdmin(dto.getId(), dto.getPwd());
			AdminResponseBothDTO responseBoth = sadmin.toAdminResponse(sadmin);
			return responseBoth;

		} else if(seniorCount == 1) { //노인이 로그인 성공했을때
			log.info("노인 로그인 성공");
			Senior senior = seniorRepo.findBySenior(dto.getId(), dto.getPwd());
			AdminResponseBothDTO responseBoth = senior.responseBothSeni(senior);
			return responseBoth;
			
		} else if(cReg.getRegCheck() == 2) { // 승인이 거절된 요양사의 경우 강제예외 발생
			throw new RejectedException("승인이 거절된 회원이므로 로그인 실패"); 
			
		} else if(caregiverCount == 1) { //요양사가 로그인 성공했을때
			log.info("요양사 로그인 성공");
			Caregiver caregiver = caregiverRepo.findByCaregiver(dto.getId(), dto.getPwd());
			AdminResponseBothDTO responseBoth = caregiver.responseBothCare(caregiver);
			return responseBoth;	
			
		}
		
		return null;
	}
	
	@Override
	public CaregiverStaticResponseDTO getStaticCaregiver(int year) throws Exception{
		//List<YearMonthDTO> caregiverStatic = caregiverRepo.findStaticSenior(year);
		List<YearMonth> caregiverStatic= caregiverRepo.findStaticSenior(year);
		
		// 이번달 확인
		LocalDate now = LocalDate.now();
		int thismonth = now.getMonthValue();
		CaregiverStaticResponseDTO val = new CaregiverStaticResponseDTO();
		
		caregiverStatic.stream().forEach(ele -> {
			if("01".equals(ele.getDateMonth())) {
				val.setOne(ele.getCnt());
			}else if("02".equals(ele.getDateMonth())) {
				val.setTwo(ele.getCnt());
			}else if("03".equals(ele.getDateMonth())) {
				val.setThree(ele.getCnt());
			}else if("04".equals(ele.getDateMonth())) {
				val.setFour(ele.getCnt());
			}else if("05".equals(ele.getDateMonth())) {
				val.setFive(ele.getCnt());
			}else if("06".equals(ele.getDateMonth())) {
				val.setSix(ele.getCnt());
			}else if("07".equals(ele.getDateMonth())) {
				val.setSeven(ele.getCnt());
			}else if("08".equals(ele.getDateMonth())) {
				val.setEight(ele.getCnt());
			}else if("09".equals(ele.getDateMonth())) {
				val.setNine(ele.getCnt());
			}else if("10".equals(ele.getDateMonth())) {
				val.setTen(ele.getCnt());
			}else if("11".equals(ele.getDateMonth())) {
				val.setEleven(ele.getCnt());
			}else if("12".equals(ele.getDateMonth())) {
				val.setTwelve(ele.getCnt());
			}
			 // 이번달과 일치하는 값이 있으면
			if(Integer.parseInt(ele.getDateMonth()) == thismonth){
				val.setThismonth(ele.getCnt());				
			}
		});

		int whole = caregiverRepo.findSeniorWhole();
		val.setSeniorAll(whole);
		
		return val;
	}

	@Override
	public List<WorkStaticResponseDTO> getadminWork(int year) throws Exception {
		List<WorkStatic> findAdminWork = betRepo.findAdminWork(year);
		List<WorkStaticResponseDTO> result = new ArrayList<>();
		
		findAdminWork.stream().forEach(ele -> {	
			String caregiverId= caregiverRepo.findByCareno(ele.getCareno()).getCid();
			String seniorId = seniorRepo.findBySeniorno(ele.getSeniorno()).getSid();	
			WorkStaticResponseDTO dto = new WorkStaticResponseDTO();
			dto.setCaregiverId(caregiverId);
			dto.setSeniorId(seniorId);
			dto.setDate(ele.getDate());
			if(ele.getStateck() == 1) {
				dto.setChoose("승인");
			}else if(ele.getStateck() == 2) {
				dto.setChoose("거절");
				
			}
			dto.setReason(ele.getReason());
			result.add(dto);
		});
		
		return result;
	}
}
