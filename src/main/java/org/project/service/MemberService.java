package org.project.service;

import org.project.domain.MemberVO;

public interface MemberService {
	
	public void register(MemberVO member);
	
	public String login(String id);
	
	public boolean registerIdCheck(String id);
	
	
}
