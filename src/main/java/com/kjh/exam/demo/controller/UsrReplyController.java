package com.kjh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.exam.demo.service.ReactionPointService;
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

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {

		if (Ut.empty(relTypeCode)) {
			return rq.jsHistoryBack("relTypeCode을(를) 입력해주세요");
		}

		if (Ut.empty(relId)) {
			return rq.jsHistoryBack("relId을(를) 입력해주세요");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("body을(를) 입력해주세요");
		}
		ResultData writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(),relTypeCode,relId,body);

		if (Ut.empty(replaceUri)) {
			switch (relTypeCode) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", relId);
				break;
			}
		}

		return rq.jsReplace(writeReplyRd.getMsg(), replaceUri);
	}
	@RequestMapping("/usr/reply/getReplyList")
	@ResponseBody
	public ResultData replyList(String relTypeCode, int relId) {
		List<Reply> replies = replyService.getForPrintReplies(relTypeCode, relId);			
		return ResultData.from("S-1",Ut.f("%d번 게시물 댓글리스트", relId),"replies",replies ); 
	}
	
	@RequestMapping("/usr/reply/modifyReply")
	@ResponseBody
	public ResultData modifyReply(String replyId, String body) {
		int replyId1 = Integer.parseInt(replyId); 
		Reply reply = replyService.getReplyById(replyId1);

		if(reply==null) {
			return ResultData.from("F-1", "존재하지 않는 댓글입니다.");
		}		
		if(rq.getLoginedMemberId() != reply.getMemberId()) {
			return ResultData.from("F-2", "댓글 수정 권한이 없습니다.");
		}
		
		ResultData modifyReplyRd= replyService.modifyReply(replyId1,body);		
		
		return modifyReplyRd;
	}
}