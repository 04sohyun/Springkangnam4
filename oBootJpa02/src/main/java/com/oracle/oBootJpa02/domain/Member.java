package com.oracle.oBootJpa02.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "member_seq_gen",
				   sequenceName = "member_seq_generator",    //매핑할 DB 시퀀스 이름 
				   initialValue = 1,
				   allocationSize = 1)

@Table(name = "member1")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "member_seq_gen")
	@Column(name = "member_id", precision = 10)
	private Long id;
	@Column(name = "user_name", length = 50)
	private String name;
	
	//FK지정 
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	//실제 테이블에서 사용하지는 않고 버퍼로만 사용 
	//주로 메모리상에서만 어떤 값을 임시로 보관 
	@Transient
	private Long teamid;
	@Transient
	private String teamname;
	
}
