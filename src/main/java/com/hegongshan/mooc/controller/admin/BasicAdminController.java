package com.hegongshan.mooc.controller.admin;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Admin;
import com.hegongshan.mooc.service.IAdminService;
import com.hegongshan.mooc.util.CryptographyUtils;
import com.hegongshan.mooc.util.FileUtils;

@Controller
@RequestMapping("/admin")
public class BasicAdminController {
	
	private static Logger logger = LoggerFactory.getLogger(BasicAdminController.class);
	
	@Resource
	private IAdminService adminService;
	//管理员登录
	@RequestMapping("")
	public ModelAndView toLoginPage(ModelAndView mv) {
		mv.setViewName("/admin/login");
		return mv;
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
					    @RequestParam("password") String password,
						HttpServletRequest request) {
		String realPassword = CryptographyUtils.encryptByMd5(password, username);
		Admin admin = new Admin(username,realPassword);
		Admin resultAdmin = adminService.getAdminByUsernameAndPassword(admin);
		if(resultAdmin == null) {
			request.setAttribute("resultAdmin", resultAdmin);
			request.setAttribute("errorMsg", "用户名或密码错误");
			return "/admin/login";
		} else {
			request.getSession().setAttribute("currentAdmin", resultAdmin);
			return "/admin/index";
		}
	}
	
	@RequestMapping("/index")
	public ModelAndView toHomePage(ModelAndView mv) {
		mv.setViewName("/admin/index");
		return mv;
	}
	
	@RequestMapping("/info")
	public String updateAdminInfo(@RequestParam(value="image",required=false) MultipartFile image,
								  @RequestParam(value="nickname",required=false) String nickname,
								  HttpServletRequest request) {

		Admin currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		if(image == null && nickname == null) {
			request.setAttribute("mainPage", "/WEB-INF/jsp/admin/center/admin_info.jsp");
		} else {
			Admin admin = new Admin();
			admin.setAdminId(currentAdmin.getAdminId());
			if(!image.isEmpty()) {				
				try {
					String relativeFilePath = FileUtils.uploadImage(image);
					admin.setImageSrc(relativeFilePath);
				} catch (IllegalStateException | IOException e) {
					logger.error("fail:upload image",e);
				}
			}
			if(nickname != null) {
				admin.setNickname(nickname);
			}
			adminService.updateAdmin(admin);
	
			Admin resultAdmin = adminService.getAdminById(currentAdmin.getAdminId());
			if(resultAdmin != null) {
				request.getSession().removeAttribute("currentAdmin");
				request.getSession().setAttribute("currentAdmin", resultAdmin);
				request.setAttribute("mainPage", "/WEB-INF/jsp/admin/center/admin_info.jsp");
			}
		}
		return "/admin/index";
	}
	
	@RequestMapping("/updatepwd")
	public ModelAndView toUpdatePasswordPage(@RequestParam(value="password",required=false) String password,ModelAndView mav,HttpSession session) {
		if(password != null) {
			Admin admin = new Admin();
			Admin currentAdmin = (Admin) session.getAttribute("currentAdmin");
			String encryptPassword = CryptographyUtils.encryptByMd5(password,currentAdmin.getUsername());
			admin.setAdminId(currentAdmin.getAdminId());
			admin.setPassword(encryptPassword);
			adminService.updateAdmin(admin);
		} else {
			mav.addObject("mainPage", "/WEB-INF/jsp/admin/center/updatepwd.jsp");
		}
		mav.setViewName("/admin/index");
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
}