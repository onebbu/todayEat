package com.mycompany.myeat.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mycompany.myeat.member.vo.MemberVO;

public interface MemberService {
	 public List listMembers() throws DataAccessException;
	 public MemberVO login(MemberVO memberVO) throws Exception;
}
