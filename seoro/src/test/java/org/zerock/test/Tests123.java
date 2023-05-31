package org.zerock.test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.betSeniorCare.repository.BetRepo;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;
import org.zerock.domain.searched.repository.SearchRepo;
import org.zerock.domain.senior.repository.SeniorRepo;

import lombok.extern.java.Log;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@Log
@Commit
public class Tests123 {
	
	@Autowired
	SeniorRepo srepo;
	
	@Autowired
	SearchRepo searchRepo;
	
	@Autowired
	CaregiverRepo caregiverRepo;
	
	@Autowired
	BetRepo brepo;
	

	
//	@Test
//	public void seniorBet() {
//		
//		//테스트 진행시 필독사항: 해당 클래스의 @Column(nullable = false)부분에 대해 주석을 달아놓고 테스트 수행해야 함.
//		//					위 주석을 달아놓지 않으면, 테스트실패. 이유? null값이 아니어야 할 곳에 null이 들어갔기 때문임. 
//		//					주석을 달아놓지 않고 테스트하려면, 밑에 nullable=false 부분에 대해 각각 set을 수행해야함.
//		
//		Senior senior = new Senior();
//		
//		
//		Caregiver caregiver = new Caregiver();
//		caregiver.setName("홍순신");
//		caregiver.setAddress("분당구");
//		
//		BetSeniorCare bet = new BetSeniorCare();
//		bet.setMonth(3);
//		
//		bet.setSenior(senior);
//		bet.setCaregiver(caregiver);
//		
//		
//		brepo.save(bet);
//		
//	}
	
//	@Test
//	public void seniorSearch() {
//		
//		Senior senior = new Senior();
//		senior.setName("장원영");
//		
//		Searched search = new Searched();
//		search.setAddress("성주읍");
//		
//		search.setSenior(senior);
//		
//		searchRepo.save(search);
//		
//		
//	}
	
//	@Test
//	public void waitCare2() {
//		
//		BigDecimal val1 = BigDecimal.valueOf(12.12);
//		String now = "2009-03-20 10:20:30.0";
//		Timestamp val2 = Timestamp.valueOf(now);
//	
//		
//		Caregiver caregiver = new Caregiver();
//		caregiver.setName("박지석");
//		caregiver.setBirth("19871029");
//		caregiver.setGender(0);
//		caregiver.setTel("01012341234");
//		caregiver.setAddress("인천특별시");
//		caregiver.setLati(val1);
//		caregiver.setLon(val1);
//		caregiver.setRegdate(val2);
//		caregiver.setCid("123");
//		caregiver.setPwd("1234");
//		caregiver.setRegCheck(1);
//		caregiver.setCertilmage("me");
//		
//		caregiverRepo.save(caregiver);
//		
//		}
	
//	@Test
//	public void updateCare() {
//		
//		Long careno = 1L;
//		int count = caregiverRepo.changeCare(careno, 0);
//		log.info("update count: " + count);
//	}
		
	}
		
	




