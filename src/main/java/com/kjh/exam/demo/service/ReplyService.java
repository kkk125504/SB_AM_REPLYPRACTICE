package com.kjh.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.ReplyRepository;
import com.kjh.exam.demo.util.Ut;
import com.kjh.exam.demo.vo.Reply;
import com.kjh.exam.demo.vo.ResultData;
@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	public ResultData writeReply(int actorId, String relTypeCode, int relId, String body) {
		
		replyRepository.writeReply(actorId,relTypeCode, relId, body);
		
		int id = replyRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 댓글이 등록되었습니다", id), "id", id);
	}

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
	
		return replyRepository.getForPrintReplies(relTypeCode,relId);
	}

	public ResultData modifyReply(int replyId, String body) {
		
		replyRepository.modifyReply(replyId,body);
		return ResultData.from("S-1", Ut.f("%d번 댓글 수정", replyId));
	}

	public Reply getReplyById(int replyId) {
		
		return replyRepository.getReplyById(replyId);
	}

}
