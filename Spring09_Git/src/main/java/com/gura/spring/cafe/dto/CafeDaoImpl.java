package com.gura.spring.cafe.dto;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring.cafe.dao.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<CafeDto> getList() {
		List<CafeDto> list = session.selectList("cafe.getList");
		return list;
	}

	@Override
	public void insert(CafeDto dto) {
		session.insert("cafe.insert",dto);		
	}
}
