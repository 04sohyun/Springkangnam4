package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootHello.domain.Member1;

@Repository
public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member1> store = new HashMap<>();
	private static long sequence = 0L;
	
	
	@Override
	public Member1 save(Member1 member1) {
		System.out.println("MemoryMemberRepository save start...");
		member1.setId(++sequence);
		store.put(member1.getId(), member1);
		return null;
	}
	
	@Override
	public List<Member1> findAll(){
		System.out.println("MemoryMemberRepository findAll start..findAll.");
		
		List<Member1> listMember = new ArrayList<>(store.values()); //Map께열에서 values하면 값만 가져온다. 가져와서 ListMember에 넣어준다.
		System.out.println("MemoryMemberRepository findAll slistMember.size() : "+listMember.size());
		
		return listMember;
	}
	
	
	
}
