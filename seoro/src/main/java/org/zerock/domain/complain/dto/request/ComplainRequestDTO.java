package org.zerock.domain.complain.dto.request;

import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;
import org.zerock.domain.complain.entity.Complain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ComplainRequestDTO {
	private long careno;
	private String content;
	
	public Complain toComplainEntity(ComplainRequestDTO dto) {
		return Complain.builder()
				.content(dto.getContent())
				.stateck(0)
				.build();
	}
}
