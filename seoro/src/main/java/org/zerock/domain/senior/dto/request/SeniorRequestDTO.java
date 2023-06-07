package org.zerock.domain.senior.dto.request;

import java.math.BigDecimal;


import org.zerock.domain.senior.entity.Senior;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeniorRequestDTO {
	
	private String id;
	private String pwd;
	private String name;
	private String spec1;
	private String spec2;
	private String spec3;
	private String tel;
	private String telCare; //노인 보호자 연락처
	private String address;
	private String lon; 
	private String lati;
	private String birth;
	private String gender;
	
	public Senior seniorForEntity(SeniorRequestDTO dto) {
		
		return Senior.builder()
				.sid(dto.getId())
				.pwd(dto.getPwd())
				.name(dto.getName())
				.spec1(dto.getSpec1())
				.spec2(dto.getSpec2())
				.spec3(dto.getSpec3())
				.tel(dto.getTel())
				.telCare(dto.getTelCare())
				.address(dto.getAddress())
				.lon(BigDecimal.valueOf(Double.valueOf(dto.getLon())))
				.lati(BigDecimal.valueOf(Double.valueOf(dto.getLati())))
				.birth(dto.getBirth())
				.gender(Integer.parseInt(dto.getBirth()))
				.build();
	}	
}
