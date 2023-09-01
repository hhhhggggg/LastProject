package org.project.mapper;

import java.util.List;

import org.project.domain.MemberVO;

public interface MemberMapper {
	
	public List<MemberVO> getList();
	
	public void insert(MemberVO member);
	
	public String selectLogin(String id);

	public int selectIdCheck(String id);

}
