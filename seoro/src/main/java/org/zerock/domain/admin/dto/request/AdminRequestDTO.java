package org.zerock.domain.admin.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminRequestDTO {
	
	private String sid; //클라이언트로부터 노인 아이디를 입력받았을때
	private String spwd;//클라이언트로부터 노인 비번을 입력받았을때
	private String cid;//클라이언트로부터 요양사 아이디를 입력받았을때
	private String cpwd;//클라이언트로부터 요양사 비번을 입력받았을때
}
