package com.gura.spring.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.cafe.dao.CafeDto;
import com.gura.spring.cafe.dto.CafeDao;
@Component
public class CafeServiceImpl implements CafeService{

	@Autowired
	private CafeDao cafeDao;

	@Override
	public ModelAndView getList() {
		List<CafeDto> list = cafeDao.getList();
		ModelAndView mView=new ModelAndView();
		mView.addObject("list", list);
		return mView;
	}

	@Override
	public void insert(CafeDto dto) {
		cafeDao.insert(dto);		
	}

	@Override
	public ModelAndView getData(int num) {
		CafeDto dto= cafeDao.getData(num);
		cafeDao.increaseViewCount(num);
		ModelAndView mView = new ModelAndView();
		mView.addObject("dto", dto);
		return mView;
	}

	@Override
	public void update(CafeDto dto) {
		cafeDao.update(dto);		
	}

	@Override
	public void delete(int num) {
		cafeDao.delete(num);
	}

	@Override
	public ModelAndView updateForm(int num) {
		CafeDto dto = cafeDao.getData(num);
		ModelAndView mView =  new ModelAndView();
		mView.addObject("dto", dto);
		return mView;
	}

}
