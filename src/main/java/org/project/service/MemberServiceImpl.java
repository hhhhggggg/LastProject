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
	
	private MemberMapper mapper;

	@Override
	public void register(MemberVO member) {
		log.info(member);
		mapper.insert(member);
	}

	@Override
	public String login(String id) {
		
		return mapper.selectLogin(id);
	}

	@Override
	public boolean registerIdCheck(String id) {
		boolean result = false;
		
		if(mapper.selectIdCheck(id)==0) result = true;
		
		return result;
	}
	
	
	
}
