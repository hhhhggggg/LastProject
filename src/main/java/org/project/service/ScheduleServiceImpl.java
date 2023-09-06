package org.project.service;

import org.project.domain.ScheduleVO;
import org.project.mapper.MemberMapper;
import org.project.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleMapper {
	private ScheduleMapper schedulemapper;

	@Override
	public int selectCnt() {
		
		return 0;
	}

	@Override
	public ScheduleVO getCategory(String genre) {
		
		return null ;
	}

	@Override
	public String genre() {
		
		return null;
	}
	
	
}
