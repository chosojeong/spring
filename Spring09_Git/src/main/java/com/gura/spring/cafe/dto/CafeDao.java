package com.gura.spring.cafe.dto;

import java.util.List;

import com.gura.spring.cafe.dao.CafeDto;

public interface CafeDao {
	public List<CafeDto> getList();
	public void insert(CafeDto dto);
}
