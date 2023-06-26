package org.zerock.domain.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.zerock.domain.admin.dto.response.AdminResponseBothDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ADMIN")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sadmin {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminno;
	
	@Column(nullable = false)
	private String aid;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false)
	private String roles;
	
	public AdminResponseBothDTO toAdminResponse(Sadmin sad) {
		return AdminResponseBothDTO.builder()
				.numberPk(String.valueOf(sad.getAdminno()))
				.roles(sad.getRoles())
				.build();
	} 
	
}
