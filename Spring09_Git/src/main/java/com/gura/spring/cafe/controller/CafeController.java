package com.gura.spring.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService cafeService;
	
	@RequestMapping("cafe/list")
	public ModelAndView list(){
		ModelAndView mView=cafeService.getList();
		mView.setViewName("cafe/list");
		return mView;
	}
}
