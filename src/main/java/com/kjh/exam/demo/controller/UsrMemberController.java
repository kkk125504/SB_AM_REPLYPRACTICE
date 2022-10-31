package com.kjh.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.exam.demo.service.MemberService;
import com.kjh.exam.demo.util.Ut;
import com.kjh.exam.demo.vo.Member;
import com.kjh.exam.demo.vo.ResultData;
import com.kjh.exam.demo.vo.Rq;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private Rq rq;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력 해주세요.");
		}

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력 해주세요.");
		}

		if (Ut.empty(name)) {
			return ResultData.from("F-3", "이름을 입력 해주세요.");
		}

		if (Ut.empty(nickname)) {
			return ResultData.from("F-4", "닉네임 입력 해주세요.");
		}

		if (Ut.empty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력 해주세요.");
		}

		if (Ut.empty(email)) {
			return ResultData.from("F-6", "이메일을 입력 해주세요.");
		}
		// S-1
		// 회원가입이 완료되었습니다
		// F-1~8
		// 실패
		ResultData<Integer> joinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return (ResultData) joinRd;
		}

		Member member = memberService.getMemberById(joinRd.getData1());

		return ResultData.newData(joinRd, "member", member);
	}

	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그인 되었습니다.");
		}

		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("아이디를 입력 해주세요.");
		}

		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("비밀번호를 입력 해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack(Ut.f("해당하는 아이디(%s)를 찾을수 없습니다.", loginId));
		}
		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		rq.login(member);

		return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getName()), "/");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("로그아웃 상태 입니다.");
		}

		rq.logout();

		return Ut.jsReplace("로그아웃 했습니다.", "/");
	}
}