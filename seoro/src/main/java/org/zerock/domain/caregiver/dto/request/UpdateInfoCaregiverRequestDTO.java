package org.zerock.domain.caregiver.dto.request;

import org.zerock.domain.caregiver.entity.Caregiver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UpdateInfoCaregiverRequestDTO {
	private String char1;
	private String char2;
	private String char3;
	private String workday;
	private String info;
	private String workTime;
	private String exp;
	private String certifi;
	private String good;
	private String goal;
	private String service;
	
	public Caregiver toCaregiverEntity(UpdateInfoCaregiverRequestDTO dto) {
		return Caregiver.builder()
				.char1(dto.getChar1())
				.char2(dto.getChar2())
				.char3(dto.getChar3())
				.workday(Integer.parseInt(dto.getWorkday()))
				.info(dto.getInfo())
				.workTime(dto.getWorkTime())
				.exp(dto.getExp())
				.certifi(dto.getCertifi())
				.good(dto.getGood())
				.goal(dto.getGoal())
				.service(dto.getService())
				.build();
	}
}
