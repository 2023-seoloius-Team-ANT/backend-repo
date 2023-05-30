package org.zerock.domain.caregiver.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;
import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	//private String profile;
	
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
	
	@CreationTimestamp
	private LocalDateTime regdate;
	
	@Column(nullable = false)
	private String cid;
	
	@Column(nullable = false)
	private String pwd;
	
	private int workday;
	
	@Column(nullable = false)
	private int regCheck; // 0은 기본 상태, 1은 승인 상태, 2는 거절 상태를 의미합니다.
	
	//@Column(nullable = false)
	//private String certilmage;
	
	public CaregiverResponseDTO toCaregiverResponseDTO(Caregiver cg) {
		return CaregiverResponseDTO.builder()
				.careno(cg.getCareno())
				.name(cg.getName())
				.char1(cg.getChar1())
				.char2(cg.getChar2())
				.char3(cg.getChar3())
				.worktime(cg.getWorkTime())
				.workday(cg.getWorkday())
				.build();
				
	}
	
}
