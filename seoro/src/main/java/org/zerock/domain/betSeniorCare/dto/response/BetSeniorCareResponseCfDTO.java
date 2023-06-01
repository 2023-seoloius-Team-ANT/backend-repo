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
public class BetSeniorCareResponseCfDTO {
	private String name;
	private int age;
	private String gender;
	private int month;
}
