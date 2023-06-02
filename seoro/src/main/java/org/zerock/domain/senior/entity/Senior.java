package org.zerock.domain.senior.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;
import org.zerock.domain.searched.entity.Searched;
import org.zerock.domain.senior.dto.response.SeniorResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SENIOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Senior {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seniorno;
	
	@OneToMany(mappedBy = "senior")
	List<BetSeniorCare> betSeniors = new ArrayList<BetSeniorCare>();
	
	@OneToMany(mappedBy = "senior")
	List<Searched> searchSeniors = new ArrayList<Searched>();
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String birth;
	
	@Column(nullable = false)
	private int gender;
	
	@Column(nullable = false)
	private String tel;
	
	private String telCare;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private BigDecimal lon;
	
	@Column(nullable = false)
	private BigDecimal lati;
	
	@Column(nullable = false)
	private LocalDateTime regdate;
	
	private String spec1;
	private String spec2;
	private String spec3;
	
	@Column(nullable = false)
	private String sid;
	
	@Column(nullable = false)
	private String pwd;
	
	public SeniorResponseDTO responseSeniorDto(Senior senior) {
		
		BetSeniorCare betSeniorCare = new BetSeniorCare();
		
		return SeniorResponseDTO.builder()
				.name(senior.getName())
				.birth(senior.getBirth())
				.gender(String.valueOf(senior.getGender()))
				.tel(senior.getTel())
				.telCare(senior.getTelCare())
				.conmonth(String.valueOf(betSeniorCare.getYear()) + "년" + " " + String.valueOf(betSeniorCare.getMonth()) + "월")
				.spec1(senior.getSpec1())
				.spec2(senior.getSpec2())
				.spec3(senior.getSpec3())
				.address(senior.getAddress())
				.lon(senior.getLon().toString())
				.lati(senior.getLati().toString())
				.build();
	}

}
