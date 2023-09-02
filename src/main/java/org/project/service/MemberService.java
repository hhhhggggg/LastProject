package org.project.service;

import org.project.domain.MemberVO;

public interface MemberService {
	
	public void register(MemberVO member);
	
	public String login(String id, String pw);
	
	public boolean registerIdCheck(String id);
	
	public MemberVO getUser(String id);
	
}
