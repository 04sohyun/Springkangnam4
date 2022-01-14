package com.oracle.oBootJpa02.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.service.MemberService;


@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//controller에서 service까지 연결 
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

//	@GetMapping("/")
//	public String home() {
//		System.out.println("MemberController home Start...");
//		return "index";
//	}
	
	@GetMapping(value="/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new start...");
		return "members/createMemberForm";
	}
	
	@PostMapping(value="/members/save")
	public String create(Member member) {
		System.out.println("MemberController create start...");
		System.out.println("member.getTeamname()->"+member.getTeamname());
		System.out.println("member.getname()->"+member.getName());
		memberService.join(member);
		return "redirect:/";
	}
	
	@GetMapping(value="/members")
	public String list(Model model) {
		List<Member> memberList =memberService.getListAllMember();
		System.out.println("memberList.get(0).getName()->"+memberList.get(0).getName());
		System.out.println("memberList.get(0).getTeamn().getName()->"+memberList.get(0).getTeam().getName());
		//System.out.println("memberList.get(1).getName()->"+memberList.get(1).getName());
		//System.out.println("memberList.get(1).getTeamn().getName()->"+memberList.get(1).getTeamn().getName());
		model.addAttribute("members", memberList);
		return "members/memberList";
	}
	
	@GetMapping(value="/memberModifyForm")
	public String memberModifyForm(Long id, Model model) {
		System.out.println("MemberController memberModify id->"+id);
		Member member = memberService.findByMember(id);		
		System.out.println("member.getId()->"+member.getId());
		System.out.println("member.getName()->"+member.getName());
		System.out.println("member.getTeam().getName()->"+member.getTeam().getName());
		model.addAttribute("member", member);
		return "members/memberModify";
	}
	
	@GetMapping(value="/members/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberController memberUpdate id->"+member.getId());
		System.out.println("MemberController memberUpdate member.getName()->"+member.getName());
		System.out.println("MemberController memberUpdate member.getTeamname()->"+member.getTeamname());
		memberService.memberUpdate(member);
		return "redirect:/members";
	}
}
