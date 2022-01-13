package com.oracle.oBootDBConnect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootDBConnect.repository.JdbcMemberRepository;
import com.oracle.oBootDBConnect.repository.MemberRepository;
import com.oracle.oBootDBConnect.repository.MemoryMemberRepository;

//@Repository로 component 하지 않은 경우 configuration으로 빈으로 등록해서 db 연결해준다. 
@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberRepository memberRepository() {
		//return new MemoryMemberRepository();
		return new JdbcMemberRepository(dataSource);
	}
}
