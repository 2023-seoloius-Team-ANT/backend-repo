package org.zerock.domain.senior.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeniorResponseDTO {
	
	private String name;
	private String birth;
	private String gender;
	private String tel;
	private String telCare;
	private String conmonth; //노인의 신청날짜를 의미함.ERD의 betseniorcare 테이블에서 year랑 month 불러와서 이 2개를 결합하면 된다.
	private String spec1;
	private String spec2;
	private String spec3;
	private String address;
	private String lon;
	private String lati;
	
	
	
	
	
}
