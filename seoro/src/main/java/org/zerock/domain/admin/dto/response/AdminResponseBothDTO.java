package org.zerock.domain.admin.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminResponseBothDTO {
	
	private String numberPk;
	private String name;
	private String roles;
	private String address;
	private String lon;
	private String lati;
}
