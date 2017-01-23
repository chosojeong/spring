package com.gura.spring.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.cafe.dao.CafeDto;


public interface CafeService {
	public ModelAndView getList();
	public void insert(CafeDto dto);	
}
