package com.gura.spring.users.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.users.dao.UsersDao;
import com.gura.spring.users.dto.UsersDto;

//component 스캔 시 bean 이 될 수 있도록
@Component
public class UserServiceImpl implements UsersService{
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public void insert(UsersDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelAndView isValid(UsersDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UsersDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> canUseId(String id) {
		//아이디 사용가능 여부를 리턴 받는다.
		boolean canUse = usersDao.canUseId(id);
		//결과값을 Map 에 담는다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("canUse", canUse);
		//Map 리턴
		return map;
	}

}
