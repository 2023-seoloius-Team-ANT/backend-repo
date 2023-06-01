package org.zerock.domain.betSeniorCare.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.senior.entity.Senior;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BETSENIORCARE")
public class BetSeniorCare {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long conno;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "seniorno")
	private Senior senior;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "careno")
	private Caregiver caregiver;
	
	@Column(nullable = false)
	private int month;
	
	@Column(nullable = false)
	private int year;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	
	@Column(nullable = false) 
	private int stateck; // 0은 기본 상태, 1은 승인 상태, 2는 거절 상태를 의미합니다.
	
}
