package org.zerock.domain.betSeniorCare.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.betSeniorCare.dto.request.BetSeniorCareRequestDTO;
import org.zerock.domain.betSeniorCare.dto.response.BetSeniorCareResponseCfDTO;
import org.zerock.domain.betSeniorCare.dto.response.BetSeniorCareResponseRsDTO;
import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;
import org.zerock.domain.betSeniorCare.repository.BetRepo;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.senior.entity.Senior;
import org.zerock.domain.senior.repository.SeniorRepo;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
public class BetSeniorCareServiceImpl implements BetSeniorCareService{
	
	@Autowired
	private BetRepo betRepo;
	
	@Autowired
	private SeniorRepo seniorRepo;
	

	@Override
	public void createBetSeniorCare(Senior senior, Caregiver caregiver, BetSeniorCareRequestDTO dto) throws Exception {
		if(senior == null) {
			throw new Exception("유효하지 않은 노인 아이디 정보입니다.");
		}
		if(caregiver == null) {
			throw new Exception("유효하지 않은 요양사 아이디 정보입니다.");
		}
		
		Optional<BetSeniorCare> dupliCk = betRepo.duplicateCk(Integer.parseInt(dto.getYear()), Integer.parseInt(dto.getMonth()), caregiver.getCareno(), senior.getSeniorno());
		
		if(dupliCk.isPresent()) {
			throw new Exception("이미 해당 년월에 예약한 내역이 존재합니다.");
		}else {
			BetSeniorCare betSeniorCare = new BetSeniorCare();
			betSeniorCare.setCaregiver(caregiver);
			betSeniorCare.setSenior(senior);
			betSeniorCare.setMonth(Integer.parseInt(dto.getMonth()));
			betSeniorCare.setYear(Integer.parseInt(dto.getYear()));
			betSeniorCare.setStateck(0); // 0은 디폴트 (초기 설정)
			
			betRepo.save(betSeniorCare);
		}
		
	}


	@Override
	public void acceptConnect(long conno) throws Exception {
		Optional<BetSeniorCare> updateAccept = betRepo.findById(conno);
		
		updateAccept.ifPresent(acc -> {
			acc.setStateck(1); // 수락으로 변경
			betRepo.save(acc);
		});
	}


	@Override
	public void declineConnect(long conno) throws Exception {
		Optional<BetSeniorCare> updateDecline = betRepo.findById(conno);
		
		updateDecline.ifPresent(acc -> {
			acc.setStateck(2); // 거절으로 변경
			betRepo.save(acc);
		});
				
	}


	@Override
	public List<BetSeniorCareResponseRsDTO> getreserveList(long careno) throws Exception {
		Senior seniors = new Senior();
		
		List<Long> strReserveList = betRepo.findByCarenoStateckRsDESC(careno); // 쿼리에 해당하는 bet어쩌고의 pk
		
		List<BetSeniorCareResponseRsDTO> reserveListAll = new ArrayList<>();
		
		
		
		for (Long reservEle: strReserveList) { // pk 하나씩 확인
			 BetSeniorCare betSeniorCare = betRepo.findById(reservEle).get();
			 seniors = seniorRepo.findById(betSeniorCare.getSenior().getSeniorno()).get(); // 중간 테이블에 담긴 pk를 통해 senior유저의 정보를 찾는다.
			 BetSeniorCareResponseRsDTO innerbet = new BetSeniorCareResponseRsDTO();
			 innerbet.setConno(betSeniorCare.getConno()); // bet어쩌고의 pk
			 innerbet.setName(seniors.getName());
			 innerbet.setSeniorno(betSeniorCare.getSenior().getSeniorno());
			 innerbet.setYear(betSeniorCare.getYear());
			 innerbet.setMonth(betSeniorCare.getMonth());
			 
			 // 성별
			 if(seniors.getGender() == 0) { // 남성
				 innerbet.setGender("남");
			 } else {
				 innerbet.setGender("여");
			 }
			 
			 LocalDate now = LocalDate.now();	
			 int year = now.getYear(); // 올해 년도
			 String birth = seniors.getBirth(); // ex) 19981119 형태
			 int birthYear = Integer.parseInt(birth.substring(0,4)); // 1998만 뽑아옴
			 innerbet.setAge(year - birthYear +1); // 나이
			 
			 reserveListAll.add(innerbet);
		}
		
		return reserveListAll;
	}


	@Override
	public List<BetSeniorCareResponseCfDTO> getconfirmList(long careno, int yearfilter) throws Exception {
		Senior seniors = new Senior();
		
		List<Long> strReserveList = betRepo.findByCarenoStateckCfDESC(careno, yearfilter); // 쿼리에 해당하는 bet어쩌고의 pk
		
		List<BetSeniorCareResponseCfDTO> reserveListAll = new ArrayList<>();
		
		
		for (Long reservEle: strReserveList) { // pk 하나씩 확인
			 BetSeniorCare betSeniorCare = betRepo.findById(reservEle).get(); // bet 테이블에 해당하는 정보가 담겨있음
			 seniors = seniorRepo.findById(betSeniorCare.getSenior().getSeniorno()).get(); // 중간 테이블에 담긴 pk를 통해 senior유저의 정보를 찾는다.
			 BetSeniorCareResponseCfDTO innerbet = new BetSeniorCareResponseCfDTO();
			 innerbet.setName(seniors.getName());
			 innerbet.setMonth(betSeniorCare.getMonth());
			 // 성별
			 if(seniors.getGender() == 0) { // 남성
				 innerbet.setGender("남");
			 } else {
				 innerbet.setGender("여");
			 }
			 
			 LocalDate now = LocalDate.now();	
			 int year = now.getYear(); // 올해 년도
			 String birth = seniors.getBirth(); // ex) 19981119 형태
			 int birthYear = Integer.parseInt(birth.substring(0,4)); // 1998만 뽑아옴
			 innerbet.setAge(year - birthYear +1); // 나이
			 
			 reserveListAll.add(innerbet);
		}
		
		return reserveListAll;
	}




}
