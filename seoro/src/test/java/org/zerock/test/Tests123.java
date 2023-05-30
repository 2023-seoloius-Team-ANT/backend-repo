//package org.zerock.test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Commit;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;
//import org.zerock.domain.betSeniorCare.repository.BetRepo;
//import org.zerock.domain.caregiver.entity.Caregiver;
//import org.zerock.domain.caregiver.repository.CaregiverRepo;
//import org.zerock.domain.searched.entity.Searched;
//import org.zerock.domain.searched.repository.SearchRepo;
//import org.zerock.domain.senior.entity.Senior;
//import org.zerock.domain.senior.repository.SeniorRepo;
//
//import lombok.extern.java.Log;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//@Log
//@Commit
//
//public class Tests123 {
//	
//	@Autowired
//	SeniorRepo srepo;
//	
//	@Autowired
//	SearchRepo searchRepo;
//	
//	@Autowired
//	CaregiverRepo caregiverRepo;
//	
//	@Autowired
//	BetRepo brepo;
//	
//
//	
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
//	
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
//	
//	@Test
//	public void waitCare2() {
//		
//		
//		List<Caregiver> caregiverList = new ArrayList<>();
//		caregiverList = caregiverRepo.getWaitCare(0);
//		
//		for(Caregiver caregiver : caregiverList) {
//			System.out.println(caregiver.getName());
//			System.out.println(caregiver.getRegCheck());
//		}
//		
//	}
//		
//	
//
//
//
//}
