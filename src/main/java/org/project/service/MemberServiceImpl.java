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
		//만약에 유저 정보가 있다면
		if (membervo != null) {
			//유저 타입도 같고 패스워드도 맞으면 
			if (membervo.getPw().equals(pw) && membervo.getChecked()==checked) {
				//성공
				return membervo.getId();
			}
			else if (!(membervo.getPw().equals(pw))) {
				return "패스워드가 다릅니다.";
			}
			else if (membervo.getChecked()!=checked) {
				return "유저 타입이 다릅니다.";
			}
		}
		return "없는 아이디 입니다.";
	}

	@Override
	public boolean registerIdCheck(String id) {
		boolean result = false;
		
		if(membermapper.IsIdValid(id)==0) result = true;
		
		return result;
	}
	@Override
	public boolean registerEmailCheck(String email) {
		boolean result = false;
		
		if(membermapper.IsEmailValid(email)==0) result = true;
		
		return result;
	}
	

	@Override
	public MemberVO getUserInfo(String id) {
		return membermapper.getUserInfo(id);
	}
	@Override
	public String findId(String name, String email, String phone) {
		String foundId = membermapper.findId(name, email, phone);
		return foundId;
	        // MemberMapper를 사용하여 이메일을 기반으로 아이디를 찾는 로직 구현
	    }

}
