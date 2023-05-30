package org.zerock.domain.senior.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;
import org.zerock.domain.searched.entity.Searched;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SENIOR")

public class Senior {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seniorno;
	
	@OneToMany(mappedBy = "senior")
	List<BetSeniorCare> betSeniors = new ArrayList<BetSeniorCare>();
	
	@OneToMany(mappedBy = "senior")
	List<Searched> searchSeniors = new ArrayList<Searched>();
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Timestamp birth;
	
	@Column(nullable = false)
	private int gender;
	
	@Column(nullable = false)
	private String tel;
	
	private String telCare;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private BigDecimal lon;
	
	@Column(nullable = false)
	private BigDecimal lati;
	
	@Column(nullable = false)
	private Timestamp regdate;
	
	private String spec1;
	private String spec2;
	private String spec3;
	
	@Column(nullable = false)
	private String sid;
	
	@Column(nullable = false)
	private String pwd;

}
