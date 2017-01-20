package com.gura.spring.users.service;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.users.dto.UsersDto;

public interface UsersService {
	public void insert(UsersDto dto);
	public boolean isValid(UsersDto dto);
	public void update(UsersDto dto);
	public void delete(String id);
	//ajax에서 리턴하기 쉬운 타입으로 변경
	public Map<String, Object> canUseId(String id);	
}
