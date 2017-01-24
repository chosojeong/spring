package com.gura.spring.shop.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl implements ShopDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public void deposit(String id, int money) {
		//전달 인자가 2개일때 dto / Map에 담아서 전달한다. Map에 담아도 MyBatis가 알아서 전달
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("id", id);
		param.put("money", money);
		session.insert("shop.deposit", param );
	}

	@Override
	public void widthDraw(String id, int money) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPoint(String id, int point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestDelivery() {
		// TODO Auto-generated method stub
		
	}

}
