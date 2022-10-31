package com.kjh.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.BoardRepository;
import com.kjh.exam.demo.vo.Board;

@Service
public class BoardService {

	private BoardRepository boardRepository;

	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public Board getBoardById(int boardId) {

		return boardRepository.getBoardById(boardId);
	}

}
