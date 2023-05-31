package org.zerock.domain.caregiver.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zerock.domain.admin.dto.response.AdminResponseDTO;
import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;

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
@NoArgsConstructor
@AllArgsConstructor
public class Caregiver {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long careno;
	
	@OneToMany(mappedBy = "caregiver")
	List<BetSeniorCare> betCaregivers = new ArrayList<BetSeniorCare>();
	
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
	
	private String visitTime; //요양사 q&a
	
	private String exp; //요양사 q&a
	private String certifi; //요양사 q&a
	
	private String good; //요양사 q&a
	
	private String goal; //요양사 q&a
	
	@Column(nullable = false)
	private BigDecimal lon;
	
	@Column(nullable = false)
	private BigDecimal lati;
	
	@Column(nullable = false)
	private Timestamp regdate;
	
	@Column(nullable = false)
	private String cid;
	
	@Column(nullable = false)
	private String pwd;
	
	private int workday;
	
	@Column(nullable = false)
	private int regCheck; // 0은 기본 상태, 1은 승인 상태, 2는 거절 상태를 의미합니다.
	
	@Column(nullable = false)
	private String certilmage;
	
	
	public AdminResponseDTO puttingDTO(Caregiver caregiver) {
		
		LocalDate now = LocalDate.now(); //날짜 정보를 가져옴
		int year = now.getYear(); // 현재 연도 얻기
		
		return AdminResponseDTO.builder()
				.careno(caregiver.getCareno())
				.name(caregiver.getName())
				.char1(caregiver.getChar1())
				.char2(caregiver.getChar2())
				.char3(caregiver.getChar3())
				.gender(caregiver.getGender())
				.workTime(caregiver.getWorkTime())
				.workday(caregiver.getWorkday())
				.age(year - (Integer.parseInt((caregiver.getBirth()).substring(0, 4))) + 1)
				.profile(caregiver.getProfile())
				.certifi(caregiver.getCertifi())
				.build();
	}
}
