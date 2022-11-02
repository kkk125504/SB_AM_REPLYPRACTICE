package com.kjh.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	private int boardId;
	private int hitCount;	
	private int goodReactionPoint;
	private int badReactionPoint;
	
	private String extra__writer;
	private boolean extra__actorCanDelete;
	private boolean extra__actorCanModify;
	
	public String getForPrintType1RegDate() {
		return regDate.substring(2,16).replace(" ","</br>");
	}
	
	public String getForPrintBody() {
		return body.replace("\n", "<br>");
	}
}