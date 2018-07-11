package com.hegongshan.mooc.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hegongshan.mooc.util.AuthCodeUtils;

@Controller
public class AuthCodeController {
		private static final Logger LOGGER  = LoggerFactory.getLogger(AuthCodeController.class);
		//生成验证码图片
		@RequestMapping("/authCode")
		public void getAuthImg(HttpServletResponse response,HttpSession session) {
			//禁止图片缓存
			response.setDateHeader("Expires",-1);
			response.setHeader("Cache-Control","no-cache");
			response.setHeader("Pragma","no-cache");
			
			response.setContentType("image/jpeg");
			String sAuthCode = AuthCodeUtils.getAuthCode();
			session.setAttribute("sAuthCode", sAuthCode);

			ServletOutputStream output = null;
			try {
				output = response.getOutputStream();
				ImageIO.write(AuthCodeUtils.getAuthImg(sAuthCode), "jpeg", output);
			} catch (IOException e) {
				LOGGER.error("验证码图片创建失败",e);
			} finally {
				if(output != null) {
					try {
						output.close();
					} catch (IOException e) {
						LOGGER.error("ServletOutputStream关闭失败",e);
					}
				}
			}
		}
}
