package org.zerock.domain.admin.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class AdminResponseDTO {
	
	private Long careno;
	private String name;
	private String char1;
	private String char2;
	private String char3;
	private int gender;
	private String workTime;
	private int workday;
	private int age;
	private String profile;
	private String certifi;
	
}
