package org.zerock.domain.admin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class ComplainCaregiverCntDTO {
	private int complainCnt;
	private int caregiverCnt;
	public ComplainCaregiverCntDTO() {
		complainCnt = 0;
		caregiverCnt = 0;
	}

}
