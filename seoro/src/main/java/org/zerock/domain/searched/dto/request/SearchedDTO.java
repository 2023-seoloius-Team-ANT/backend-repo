package org.zerock.domain.searched.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchedDTO {
	
	private String searchedadr; //검색한 주소
	private String lon;// 검색한 주소의 경도
	private String lati;// 검색한 주소의 위도
}
