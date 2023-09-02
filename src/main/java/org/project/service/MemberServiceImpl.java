package org.project.service;

import org.springframework.stereotype.Service;
import org.project.domain.MemberVO;
import org.project.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper membermapper;

	@Override
	public void register(MemberVO member) {
		log.info(member);
		membermapper.insert(member);
	}

	@Override
	public String login(String id, String pw, int checked) {
		MemberVO membervo = membermapper.getUserInfo(id);
		if (membervo.getPw().equals(pw) && membervo.getChecked()==checked) {
			return membervo.getId();
		}
		return null;
	}

	@Override
	public boolean registerIdCheck(String id) {
		boolean result = false;
		
		if(membermapper.IsIdValid(id)==0) result = true;
		
		return result;
	}

	@Override
	public MemberVO getUserInfo(String id) {
		return membermapper.getUserInfo(id);
	}
	
	
	
}
