package org.zerock.domain.caregiver.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.zerock.domain.admin.dto.response.AdminResponseBothDTO;
import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;
import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;
import org.zerock.domain.complain.entity.Complain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CAREGIVER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Caregiver {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long careno;
	
	@OneToMany(mappedBy = "caregiver")
	List<BetSeniorCare> betCaregivers = new ArrayList<BetSeniorCare>();
	
	@OneToMany(mappedBy = "caregiver")
	List<Complain> complain = new ArrayList<Complain>();
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String birth;
	
	@Column(nullable = false)
	private int gender;
	
	@Column(nullable = false)
	private String tel; 
	
	@Column(nullable = false)
	private String address;
	
	private String profile;
	
	private String workTime;
	
	private String char1;
	private String char2;
	private String char3;
	
	
	private String info; //요양사 q&a
	
	private String exp; //요양사 q&a
	private String certifi; //요양사 q&a
	
	private String good; //요양사 q&a
	
	private String goal; //요양사 q&a
	
	@Column(nullable = false)
	private BigDecimal lon;
	
	@Column(nullable = false)
	private BigDecimal lati;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	
	@Column(nullable = false)
	private String cid;
	
	@Column(nullable = false)
	private String pwd;
	
	private int workday;
	
	private String service;
	
	@Column(nullable = false)
	private int regCheck; // 0은 기본 상태, 1은 승인 상태, 2는 거절 상태를 의미합니다.
	
	@Column(nullable = false)
	private String certilmage;
	
	private String roles;
	
	public CaregiverResponseDTO toCaregiverResponseDTO(Caregiver cg) {
		return CaregiverResponseDTO.builder()
				.careno(cg.getCareno())
				.name(cg.getName())
				.char1(cg.getChar1())
				.char2(cg.getChar2())
				.char3(cg.getChar3())
				.worktime(cg.getWorkTime())
				.workday(cg.getWorkday())
				.info(cg.getInfo())
				.service(cg.getService())
				.exp(cg.getExp())
				.certifi(cg.getCertifi())
				.good(cg.getGood())
				.goal(cg.getGoal())
				.build();
				
	}
  
  	public AdminResponseDTO puttingDTO(Caregiver caregiver) {
		
  		LocalDateTime localReg = caregiver.getRegdate();
		LocalDate now = LocalDate.now(); //날짜 정보를 가져옴
		int year = now.getYear(); // 현재 연도 얻기
		
		return AdminResponseDTO.builder()
				.careno(String.valueOf(caregiver.getCareno()))
				.name(caregiver.getName())
				.char1(caregiver.getChar1())
				.char2(caregiver.getChar2())
				.char3(caregiver.getChar3())
				.gender(String.valueOf(caregiver.getGender()))
				.workTime(caregiver.getWorkTime())
				.workday(String.valueOf((caregiver.getWorkday())))
				.age(String.valueOf(year - (Integer.parseInt((caregiver.getBirth()).substring(0, 4))) + 1))
				.profile(caregiver.getProfile())
				.certifi(caregiver.getCertifi())
				.address(caregiver.getAddress())
				.goal(caregiver.getGoal())
				.good(caregiver.getGood())
				.regdate(localReg.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
				.tel(caregiver.getTel())
				.info(caregiver.getInfo())
				.exp(caregiver.getExp())
				.service(caregiver.getService())
				.certiImage(caregiver.getCertilmage())
				.build();
	}
  	
  	public AdminResponseBothDTO responseBothCare(Caregiver caregiver) {
  		
  		return AdminResponseBothDTO.builder()
  				.numberPk(String.valueOf(caregiver.getCareno()))
  				.name(caregiver.getName())
  				.roles(caregiver.getRoles())
  				.address(caregiver.getAddress())
  				.lon(caregiver.getLon().toString())
  				.lati(caregiver.getLati().toString())
  				.build();
  	}
	

}

