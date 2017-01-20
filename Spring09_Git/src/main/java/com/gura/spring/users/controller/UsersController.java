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
	
	//7 "/users/private/info.do" 개인정보 보기 요청 처리
	@RequestMapping("users/private/info")
	// HttpSession 선언하는 것만으로 세션 객체의 참조값이 전달된다.
	public ModelAndView info(HttpSession session){
		//1. 세션에 저장된 id 정보를 읽어온다.
		String id = (String)session.getAttribute("id");
		//2. UserDto 가 담긴 ModelAndView 객체를 리턴 받는다.
		ModelAndView mView = usersService.getData(id);
		//3. forward 이동할 경로를 담고
		mView.setViewName("users/private/info");
		//4. ModelAndView 객체를 리턴해준다.
		return mView;
	}
	
	//6 "users/signout.do" 요청처리
	@RequestMapping("users/signout")
	public ModelAndView signout(HttpSession session) {
		// 세션 초기화
		//session.invalidate();
		// 세션에서 id 정보만 지우기
		session.removeAttribute("id");
		ModelAndView mView= new ModelAndView();
		mView.addObject("msg", "로그아웃 되었습니다.");
		mView.addObject("redirectUri", session.getServletContext().getContextPath());
		mView.setViewName("users/alert");
		return mView;
	} 
	
	//5 "users/signin.do" 요청처리
	@RequestMapping("users/signin")
	public ModelAndView signin(@ModelAttribute UsersDto dto, @RequestParam String uri, HttpSession session){
		//아이디 비밀번호가 유효한지 여부를 확인한다.
		boolean isValid= usersService.isValid(dto);
		ModelAndView mView = new ModelAndView();
		if(isValid){	//아이디 비밀 번호가 맞는 정보인 경우
			//로그인 처리를 해준다.
			session.setAttribute("id", dto.getId());
			mView.addObject("msg", dto.getId() + "님 로그인 되었습니다.");
			mView.addObject("redirectUri", uri);
		}else{
			//아이디 혹은 비밀번호가 틀리다는 정보를 응답한다.
			mView.addObject("msg", "아이디 혹은 비밀번호가 틀려요");
			String location=session.getServletContext().getContextPath()+"/users/signin_form.do?uri="+uri;
			mView.addObject("redirectUri", uri);
		}
		//알림 페이지로 forward 이동 시킨다.
		mView.setViewName("users/alert");
		return mView;
	}
	
	
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
