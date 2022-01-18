package com.oracle.oBootMybatis03.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Emp {
	private int empno;
	private String job;
	private String hiredate;
	private int comm;
	private String ename;
	private int mgr;
	private int sal;
	private int deptno;
	
	//조회용 
	private String search;
	private String pageNum;
	private int start;
	private String keyword;
	private int end;
}
