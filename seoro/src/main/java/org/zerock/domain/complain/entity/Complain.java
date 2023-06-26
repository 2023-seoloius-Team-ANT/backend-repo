package org.zerock.domain.complain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;
import org.zerock.domain.caregiver.entity.Caregiver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Complain {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long complainno;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "careno")
	private Caregiver caregiver;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private int stateck; // 0은 대기상태, 1은 확인 상태를 의미합니다.
	
	@CreationTimestamp
	private LocalDateTime regdate;
	
	
}
