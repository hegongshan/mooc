package com.hegongshan.mooc.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hegongshan.mooc.entity.Student;
import com.hegongshan.mooc.service.IAdminService;
import com.hegongshan.mooc.service.IStudentService;

@Controller
@RequestMapping("/admin/student")
public class StudentAdminController {
	
	@Resource 
	private IStudentService studentService;
	@Resource 
	private IAdminService adminService;
	
	@RequestMapping("/list")
	public String listStudent(Model model) {
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("condition", "whitelist");
		List<Student> students = studentService.listStudentByCondition(conditions);
		model.addAttribute("students", students);
		model.addAttribute("mainPage", "/WEB-INF/jsp/admin/student/student_list.jsp");
		return "/admin/index";
		
	}
	
	@RequestMapping(value="/add_to_blacklist",method=RequestMethod.GET)
	public String saveToBlacklist(@RequestParam("studentId") Integer studentId) {
		
		adminService.saveToBlacklist("student",studentId);
		return "redirect:/admin/student/list";
		
	}
	
	@RequestMapping("/blacklist")
	public String blacklist(Model model) {
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("condition", "blacklist");
		List<Student> students = studentService.listStudentByCondition(conditions);
		model.addAttribute("students", students);
		model.addAttribute("mainPage", "/WEB-INF/jsp/admin/student/blacklist.jsp");
		return "/admin/index";
		
	}
	
	@RequestMapping(value="/remove_from_blacklist",method=RequestMethod.GET)
	public String removeFromBlacklist(@RequestParam("studentId") Integer studentId) {
		
		adminService.removeFromBlacklist("student",studentId);
		return "redirect:/admin/student/blacklist";
		
	}
}
