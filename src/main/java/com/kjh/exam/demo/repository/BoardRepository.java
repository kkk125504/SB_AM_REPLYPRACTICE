package com.kjh.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kjh.exam.demo.vo.Board;
@Mapper
public interface BoardRepository {

	public Board getBoardById(int boardId);
	

}
