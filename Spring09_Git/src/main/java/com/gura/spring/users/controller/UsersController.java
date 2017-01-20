package com.gura.spring.users.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.users.dto.UsersDto;
import com.gura.spring.users.service.UsersService;

//component 스캔시 bean 이 되고 또한 컨트롤러 역할을 할 수 있도록
@Controller
public class UsersController {
	//의존 객체 주입 되도록
	@Autowired
	private UsersService usersService;
	
	//4 "users/signin_form.do" 로그인 폼 요청 처리
	@RequestMapping("/users/signin_form")
	public String signinForm(HttpSession session){
		//세션 초기화 - 중복 로그인 방지
		session.invalidate();
		//뷰 페이지로 forward 이동
		return "users/signin_form";
	}
	
	//3 "/users/signup.do" 요청 처리
	@RequestMapping("/users/signup")
	public ModelAndView signup(HttpServletRequest request, @ModelAttribute UsersDto dto){
		//서비스를 이용해서 회원정보를 저장한다.
		usersService.insert(dto);
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", dto.getId() +"회원님 가입 되었습니다.");
		mView.addObject("redirectUri", request.getContextPath());
		mView.setViewName("users/alert");
		return mView;
	}
	//2
	//ajax "/users/checkid.do 요청 처리
	@RequestMapping("/users/checkid")
	@ResponseBody	//ajax?
	public Map<String, Object> checkid(@RequestParam String inputId){
															//전달되는 파라미터명과 같게 해준다.
		Map<String, Object> map=usersService.canUseId(inputId);
		//json 문자열 응답하기
		return map;
	}
	//1
	@RequestMapping("/users/signup_form")
	public String signupForm(){
		
		return "users/signup_form";
	}
}
