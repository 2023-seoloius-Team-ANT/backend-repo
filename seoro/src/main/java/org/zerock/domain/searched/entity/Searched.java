package org.zerock.domain.searched.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zerock.domain.senior.entity.Senior;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SEARCHED")
public class Searched {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long searchno;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "seniorno")
	private Senior senior;
	
	@Column(nullable = false)
	private String searchedadr;
	
	@Column(nullable = false)
	private Timestamp regdate;
	
	@Column(nullable = false)
	private BigDecimal lon;
	
	@Column(nullable = false)
	private BigDecimal lati;
	
}
