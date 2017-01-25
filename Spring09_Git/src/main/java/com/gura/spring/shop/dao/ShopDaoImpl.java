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
	public void withDraw(String id, int money) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("id", id);
		param.put("money", money);
		session.update("shop.withDraw", param);
	}

	@Override
	public void addPoint(String id, int point) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("id", id);
		param.put("point", point);
		session.update("shop.addPoint", param);
	}

	@Override
	public void deliveryRequest() {
		System.out.println("배송 요청을 했습니다.");
		//트랜젝션을 관리하는 블럭에 Custom Exception 을 발생시켜서
		//종류별로 Exception 을 핸들링 할 수 있다.
		
		//특정 조건에서 발생한다는 가정
		throw new OopsException("오늘은 눈이와서 배송을 못해요");
	}
}
