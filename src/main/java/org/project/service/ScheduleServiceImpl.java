package org.project.service;

import org.project.domain.ScheduleVO;
import org.project.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleMapper {
	private ScheduleMapper schedulemapper;

	@Override
	public int selectCnt() {
<<<<<<< HEAD
		
		return schedulemapper.selectCnt();
=======
		int result;
		result = schedulemapper.selectCnt();
		log.info(result);
		return result;
>>>>>>> 4552b4368426c55d1ff187769ef2bb2e1d865cc4
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
