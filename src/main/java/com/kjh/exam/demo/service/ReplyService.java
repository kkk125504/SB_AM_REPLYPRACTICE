package com.kjh.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.ReplyRepository;
import com.kjh.exam.demo.vo.Reply;
import com.kjh.exam.demo.vo.ResultData;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository replyRepository;

	public List<Reply> getReplyList(String relTypeCode, int relId) {

		return replyRepository.getReplyList(relTypeCode, relId);
	}

	public ResultData writeReply(int actorId, String relTypeCode, int relId, String body) {
		if (actorId == -1) {
			return ResultData.from("F-1", "로그인 후 이용가능합니다.");
		}
		replyRepository.writeReply(actorId, relTypeCode, relId, body);
		int replyId = replyRepository.getLastInsertId();
		return ResultData.from("S-1", "댓글 달기 성공","replyId",replyId );
	}
}
