package com.kjh.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.MemberRepository;
import com.kjh.exam.demo.util.Ut;
import com.kjh.exam.demo.vo.Member;
import com.kjh.exam.demo.vo.ResultData;

@Service
public class MemberService {

	MemberRepository memberRepository;

	@Autowired
	MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public ResultData<Integer> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		// 로그인아이디 중복체크
		Member existsMember = getMemberByLoginId(loginId);
		if (existsMember != null) {
			return ResultData.from("F-7", Ut.f("중복되는 아이디(%s)가 있습니다", loginId));
		}
		// 이름과 이메일 중복체크
		existsMember = getMemberByNameAndEmail(name, email);
		if (existsMember != null) {
			return ResultData.from("F-8", Ut.f("중복되는 이름(%s)과 이메일(%s)이 있습니다", name, email));
		}
		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		int id = memberRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%s님 회원가입 성공", nickname), "id", id);
	}

	public Member getMemberByLoginId(String loginId) {
		Member member = memberRepository.getMemberByLoginId(loginId);
		return member;
	}

	public Member getMemberById(int id) {
		Member member = memberRepository.getMemberById(id);
		return member;
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		Member member = memberRepository.getMemberByNameAndEmail(name, email);
		return member;
	}
}
