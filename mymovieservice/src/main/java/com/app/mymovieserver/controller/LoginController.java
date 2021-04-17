package com.app.mymovieserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.mymovieserver.dtos.LoginDto;
import com.app.mymovieserver.dtos.UserDto;


/**
 * @author aghil
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	AtomicInteger ctr=new AtomicInteger(1);

	@RequestMapping(value = "/validateuser", method = RequestMethod.POST)
	public @ResponseBody LoginDto login(@RequestBody LoginDto loginDto, HttpServletRequest request)
	{
		log.info("User Name is  " + loginDto.getEmail());
		LoginDto loginRespDto = (LoginDto) sendtoMQ(loginDto,"validateUser","loginService");
		log.info("LoginResponse :"+loginRespDto);
		if (loginRespDto !=null && loginRespDto.isAuthenticationStatus() == true) {
			HttpSession session = request.getSession(true);
			loginRespDto.setSessionid(session.getId());
			log.info("Session timeout interval " + session.getMaxInactiveInterval());
			log.info("Session created " + session.getId());
			session.setAttribute("userEmailId",loginDto.getEmail());
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			Integer ctrValue=ctr.getAndIncrement();
			Authentication authentication = new UsernamePasswordAuthenticationToken(ctrValue, "test", authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			log.error("Login failed");
		}
		return loginRespDto;
	}

	@RequestMapping(value = "/logmeout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request) {
		log.info("Logged out called");
		log.info(""+request.getSession().getAttribute("userEmailId"));
		HttpSession session = request.getSession(false);
		if(session != null){
			session.setAttribute("LOGOUTSTATUS","USER_LOGOUT");
			session.invalidate();
		}
		else{
			log.info("Session is already invalidated");
		}
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public @ResponseBody UserDto createUser(@RequestBody UserDto userDto, HttpServletRequest request)
	{
		userDto = (UserDto) sendtoMQ(userDto,"createUser","loginService");

		return userDto;
	}

}
