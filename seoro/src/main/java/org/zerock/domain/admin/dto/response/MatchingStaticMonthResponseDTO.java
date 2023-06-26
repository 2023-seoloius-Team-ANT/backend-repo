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
public class MatchingStaticMonthResponseDTO {
	private int connectAll;
	private int conenctSuccess;
	private int connectFail;

}
