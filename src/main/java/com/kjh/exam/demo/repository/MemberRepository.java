package com.kjh.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kjh.exam.demo.vo.Member;

@Mapper
public interface MemberRepository {

	public void doJoin(String loginId, String loginPw, String name, String nickname,
			String cellphoneNum, String email);

	public int getLastInsertId();

	public Member getMemberById(int id);

	public Member getMemberByLoginId(String loginId);

	public Member getMemberByNameAndEmail(String name, String email);

}
