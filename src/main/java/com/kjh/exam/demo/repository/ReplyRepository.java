package com.kjh.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kjh.exam.demo.vo.Reply;

@Mapper
public interface ReplyRepository {

	
	@Select("""
		SELECT RE.*,M.nickname AS extra__replyWriter FROM reply AS RE
		LEFT JOIN `member` AS M
		ON RE.memberId = M.id
		WHERE RE.relTypeCode=#{relTypeCode}
		AND RE.relId=#{relId}
			""")
	public List<Reply> getReplyList(String relTypeCode, int relId);

	
	@Insert("""
		INSERT INTO reply
		SET regDate = NOW(),
		updateDate = NOW(),
		memberId = #{actorId},
		relTypeCode = #{relTypeCode},
		relId = #{relId},
		`body` = #{body};
			""")
	public void writeReply(int actorId, String relTypeCode, int relId, String body);

	@Select("""
		SELECT LAST_INSERT_ID()				
			""")
	public int getLastInsertId();
}
