package org.zerock.domain.admin.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class AdminResponseDTO {
	
	private String careno;
	private String name;
	private String char1;
	private String char2;
	private String char3;
	private String gender;
	private String workTime;
	private String workday;
	private String age;
	private String profile;
	private String certifi;
	
	//추가
	private String address;
	private String goal;
	private String good;
	private String regdate;
	private String tel;
	private String info;
	private String exp;
	private String service;
	private String certiImage;
	
	
}
