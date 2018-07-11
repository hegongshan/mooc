package com.hegongshan.mooc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hegongshan.mooc.entity.Student;
import com.hegongshan.mooc.entity.Teacher;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	private static final String[] IGNORE_URI = {"/static","/index","/authCode",
									"/course/all","/course/detail","/courseType",
									"/search","/login","/resetpwd","/register","/confirm","/checkUsername"};
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = false;
		String path = request.getRequestURI();
		for(String s : IGNORE_URI) {
			if(path.contains(s)) {
				flag = true;
				break;
			}
		}
		if(path.endsWith("/admin")) {
			flag = true;
		}
		if(!flag) {
			Object user =  request.getSession().getAttribute("user");
			Object admin = request.getSession().getAttribute("currentAdmin");
			if(user == null && admin == null) {
				request.getRequestDispatcher("/index").forward(request, response);
			}
			if(user != null && user instanceof Teacher) {
				if(!path.contains("/student") && !path.contains("/admin")) {
					flag = true;
				} else {
					request.getRequestDispatcher("/index").forward(request, response);
				}
			} 
			if(user != null && user instanceof Student) {
				if(!path.contains("/teacher") && !path.contains("/admin")) {
					flag = true;
				} else {
					request.getRequestDispatcher("/index").forward(request, response);
				}
			}
			if(admin != null) {
				if(path.contains("/admin")) {
					flag = true;
				} else {
					request.getRequestDispatcher("/admin").forward(request, response);
				}
			}
		} 
		return flag;
	}
}
