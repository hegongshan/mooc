package com.hegongshan.mooc.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Teacher;
import com.hegongshan.mooc.service.IAdminService;
import com.hegongshan.mooc.service.ITeacherService;

@Controller
@RequestMapping("/admin/teacher")
public class TeacherAdminController {
	
	@Resource
	private ITeacherService teacherService;
	@Resource
	private IAdminService adminService;
	
	@RequestMapping("/list")
	public ModelAndView listTeacher(ModelAndView mv) {
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("condition", "whitelist");
		List<Teacher> teacherList = teacherService.listTeacherByCondition(conditions);
		mv.addObject("teachers", teacherList);
		mv.addObject("mainPage","/WEB-INF/jsp/admin/teacher/list_teacher.jsp");
		mv.setViewName("/admin/index");
		return mv;
	}
	
	@RequestMapping(value="/add_to_blacklist",method=RequestMethod.GET)
	public String saveToBlacklist(@RequestParam("teacherId") Integer teacherId) {
		
		adminService.saveToBlacklist("teacher",teacherId);
		return "redirect:/admin/teacher/list";
		
	}
	
	@RequestMapping("/blacklist")
	public ModelAndView blacklist(ModelAndView mv) {
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("condition", "blacklist");
		List<Teacher> teacherList = teacherService.listTeacherByCondition(conditions);
		mv.addObject("teachers", teacherList);
		mv.addObject("mainPage","/WEB-INF/jsp/admin/teacher/blacklist.jsp");
		mv.setViewName("/admin/index");
		return mv;
	}
	
	@RequestMapping(value="/remove_from_blacklist",method=RequestMethod.GET)
	public String removeFromBlacklist(@RequestParam("teacherId") Integer teacherId) {
		
		adminService.removeFromBlacklist("teacher",teacherId);
		return "redirect:/admin/teacher/blacklist";
		
	}
}
