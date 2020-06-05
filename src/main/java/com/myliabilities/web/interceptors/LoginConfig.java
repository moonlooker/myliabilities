package com.myliabilities.web.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors( InterceptorRegistry registry) {
		//注册TestInterceptor拦截器
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login",
				"/**/*.html", "/**/*.js", "/**/*.css", "/**/*.woff", "/**/*.ttf", "/register", "/error", "/submit", "/doregister");
	}
}