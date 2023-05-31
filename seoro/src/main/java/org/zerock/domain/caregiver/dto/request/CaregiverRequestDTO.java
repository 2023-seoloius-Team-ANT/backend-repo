package org.zerock.domain.caregiver.dto.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.caregiver.entity.Caregiver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CaregiverRequestDTO {
	private String cid;
	private String pwd;
	private String name;
	private String tel;
	private String address;
	private String lon;
	private String lati;
	private String birth;
	private String gender; // 0은 남자, 1은 여자입니다.
	private MultipartFile profileImage;
	private MultipartFile certiImage;
	
	// entity로 변환시키기
	public Caregiver toCaregiverEntity(CaregiverRequestDTO dto) {
		return Caregiver.builder()
				.cid(dto.getCid())
				.pwd(dto.getPwd())
				.name(dto.getName())
				.tel(dto.getTel())
				.address(dto.getAddress())
				.lon(BigDecimal.valueOf(Double.valueOf(dto.getLon())))
				.lati(BigDecimal.valueOf(Double.valueOf(dto.getLati())))
				.birth(dto.getBirth())
				.gender(Integer.parseInt(dto.getGender()))
				.build();
				
				
	}
}
