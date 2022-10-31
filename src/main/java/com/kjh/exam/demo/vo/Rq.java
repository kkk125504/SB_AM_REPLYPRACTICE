package com.kjh.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.kjh.exam.demo.service.MemberService;
import com.kjh.exam.demo.util.Ut;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		
		boolean isLogined = false;
		int loginedMemberId = -1;
		Member loginedMember = null;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember =memberService.getMemberById(loginedMemberId);
		}
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		this.req.setAttribute("rq", this);
	}

	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		println(Ut.jsHistoryBack(msg));		
	}

	public void print(String msg) {
		try {
			resp.getWriter().append(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void println(String str) {
		print(str + "\n");
	}
	
	public boolean isNotLogined() {
		return !isLogined;
	}
	
	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
		session.setAttribute("loginedMemberLoginId", member.getLoginId());		
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberLoginId");		
	}

	public String jsHistoryBackOnView(String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "usr/common/js";
	}

	public String jsHistoryBack(String msg) {		
		return Ut.jsHistoryBack(msg);
	}

	public String jsReplace(String msg, String uri) {		
		return Ut.jsReplace(msg, uri);
	}

	public String getCurrentUri() {
		String currentUri = req.getRequestURI();
		String queryString = req.getQueryString();

		if (queryString != null && queryString.length() > 0) {
			currentUri += "?" + queryString;
		}

		return currentUri;
	}

	public String getEncodedCurrentUri() {

		return Ut.getUriEncoded(getCurrentUri());
	}
	
	public void initOnBeforeActionInterceptor() {
	}

}
