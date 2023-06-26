package org.zerock.domain.complain.dto.response;

import org.zerock.domain.caregiver.dto.response.CaregiverQueResponseDTO;

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
public class ComplainResponseDTO {
	Long complainno;
	String content;
	String complainUser;
	String date;
	
}
