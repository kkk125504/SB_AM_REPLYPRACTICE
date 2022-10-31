package com.kjh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.exam.demo.service.ReplyService;
import com.kjh.exam.demo.util.Ut;
import com.kjh.exam.demo.vo.Reply;
import com.kjh.exam.demo.vo.ResultData;
import com.kjh.exam.demo.vo.Rq;

@Controller
public class UsrReplyController {

	@Autowired
	private ReplyService replyService;
	@Autowired
	private Rq rq;
	
	@RequestMapping("/usr/reply/getReplyList")
	@ResponseBody
	public ResultData replyList(String relTypeCode, int relId) {
		List<Reply> replies = replyService.getReplyList(relTypeCode, relId);			
		return ResultData.from("S-1",Ut.f("%d번 게시물 댓글리스트", relId),"replies",replies ); 
	}
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public ResultData doWrite(String relTypeCode, int relId, String body) {
		/*
		 * if (Ut.empty(relTypeCode)) { return ResultData.from("F-1",
		 * "relTypeCode을(를) 입력해주세요"); }
		  
		if (Ut.empty(relId)) { return ResultData.from("F-2", "relId을(를) 입력해주세요"); }
		
		 * if (Ut.empty(body)) { return ResultData.from("F-3", "body을(를) 입력해주세요"); }
		 */
		ResultData doWriteRd = replyService.writeReply(rq.getLoginedMemberId(),relTypeCode,relId,body);
		
		return doWriteRd;
	}
}