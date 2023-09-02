package org.project.mapper;

import org.project.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO getUser(String id);
	
	public void insert(MemberVO member);
	
	public String selectLogin(String id);

	public int selectIdCheck(String id);

}
