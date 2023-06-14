package org.zerock.domain.betSeniorCare.dto.response;

import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BetSeniorCareResponseRsDTO { // 모든 신청 현황을 불러오는데 사용하기 위한 DTO
	private long conno; // 신청형황 리스트의 pk
	private long seniorno; // 불려온 노인의 pk
	private String name;
	private int age;
	private String gender;
	private int year;
	private int month;
}
