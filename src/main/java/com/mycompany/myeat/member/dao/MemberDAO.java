package com.mycompany.myeat.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mycompany.myeat.member.vo.MemberVO;


public interface MemberDAO {
	 public List selectAllMemberList() throws DataAccessException;
	 public MemberVO loginById(MemberVO memberVO) throws DataAccessException;

}
