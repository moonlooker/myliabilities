package com.myliabilities.web.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.myliabilities.service.NowUser;

public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (NowUser.loginUser == null) {
			try {
				log.warn("无登录用户");
				response.sendRedirect("/login");
			} catch ( IOException e) {
				log.error("Redirect error !!", log);
			}
			return false;
		}

		//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
		//如果设置为true时，请求将会继续执行后面的操作
		return true;
	}

}
