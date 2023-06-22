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
public class CaregiverStaticResponseDTO {
	private long one;
	private long two;
	private long three;
	private long four;
	private long five;
	private long six;
	private long seven;
	private long eight;
	private long nine;
	private long ten;
	private long eleven;
	private long twelve;
	private long thismonth;
	private int seniorAll;
	
	// dto 디폴트 값 설정
	public CaregiverStaticResponseDTO() {
        one = 0;
        two = 0;
        three = 0;
        four = 0;
        five = 0;
        six = 0;
        seven = 0;
        eight = 0;
        nine = 0;
        ten = 0;
        eleven = 0;
        twelve = 0;
        thismonth = 0;

    }
	
	
	
}
