package org.zerock.domain.caregiver.dto.response;

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
public class CaregiverResponseDTO {
	private long careno;
	private String name;
	private String char1;
	private String char2;
	private String char3;
	private String genderStr;
	private String worktime; // 근무 시간
	private int workday; // 근무 요일 주 x일
	private int age;
	private String profile; // 버킷 생성하고 추가
	
}
