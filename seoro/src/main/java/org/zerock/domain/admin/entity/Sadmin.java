package org.zerock.domain.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ADMIN")

public class Sadmin {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminno;
	
	@Column(nullable = false)
	private String aid;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false)
	private String roles;
	
}
