package org.zerock.domain.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkStaticResponseDTO {
	private String date;
	private String caregiverId;
	private String seniorId;
	private String choose;
	private String reason;
}
