package com.hegongshan.mooc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Comment;
import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.entity.Student;
import com.hegongshan.mooc.service.ICommentService;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.IStudentService;
import com.hegongshan.mooc.util.CryptographyUtils;
import com.hegongshan.mooc.util.DateUtils;
import com.hegongshan.mooc.util.FileUtils;
import com.hegongshan.mooc.util.MailUtils;

@Controller
@RequestMapping("/student")
public class StudentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	@Resource
	private IStudentService studentService;
	@Resource
	private ICourseService courseService;
	@Resource
	private ICommentService commentService;	
	@Resource
	private JavaMailSenderImpl mailSender;
	
	@RequestMapping("/register")
	public String register(Student student,
						   @RequestParam(value="uAuthCode",required=false) String uAuthCode,
			               HttpServletRequest request) {
		if(uAuthCode == null) {
			return "/student/student_register";
		}
		
		String sAuthCode = (String) request.getSession().getAttribute("sAuthCode");
		
		if(!sAuthCode.equalsIgnoreCase(uAuthCode)) {	
			request.setAttribute("student", student);
			request.setAttribute("errorMsg", "验证码错误！");
			return "/student/student_register";
			
		}
		
		studentService.doRegister(student,request.getContextPath());
		return "/common/register_confirm";
	}
	
	@RequestMapping(value="/checkUsername")
	public String checkUsername(@RequestParam("username") String username,HttpServletResponse response) throws IOException {
		response.setDateHeader("Expires",-1);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		
		Student student = studentService.getStudentByUsername(username);
		PrintWriter out = response.getWriter();

		if(student != null) {
			out.write("isExist");
		} else {
			out.write("isNotExist");
		}
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(value="/confirm")
	public String confirm(@RequestParam("username") String username,
						  @RequestParam("activeCode") String activeCode,
						  HttpServletRequest request) {
		Student student = studentService.doConfirm(username);
		//验证用户是否存在
		if(student != null) {
			Date date = new Date();
			//验证激活码是否过期
			if(date.before(DateUtils.operateDate(student.getRegisterTime(), 1))) {
				//验证激活码是否正确
				if(student.getActiveCode().equals(activeCode)) {
					//激活
					student.setStatus(1);
					studentService.doUpdate(student);
					
					request.getSession().setAttribute("user", student);
					request.getSession().setAttribute("role", "student");
					return "redirect:/index";
				} else {
					request.setAttribute("errorMsg", "激活码不正确！");
					return "/common/register_fail";
				}
			} else {
				//验证码过期，删除注册信息
				studentService.doRemove(student.getStudentId());
				request.setAttribute("errorMsg", "激活码已过期，请重新注册！");
				return "/common/register_fail";
			}
		} else {
			request.setAttribute("errorMsg", "用户不存在！");
			return "/common/register_fail";
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Student student,
						@RequestParam(value="rememberMe",required=false) String rememberMe,
						HttpServletRequest request,
						HttpServletResponse response) {
		String orignalPassword = student.getPassword();
		Student resultStudent = studentService.doLogin(student);
		if(resultStudent == null) {
			request.setAttribute("errorUser", student);
			request.setAttribute("errorMsg", "用户名或密码错误！");
			return "/common/login";
		}
		
		if(resultStudent.getStatus() == 0) {
			request.setAttribute("errorMsg", "该账户尚未激活，请登录邮箱激活");
			return "/common/login";
		}
		
		if(resultStudent.getStatus() == 2) {
			request.setAttribute("errorMsg", "该账户已被管理员封禁，请联系管理员解封");
			return "/common/login";
		}
		
		request.getSession().setAttribute("user", resultStudent);

		if(rememberMe != null &&"remember-me".equals(rememberMe)) {
			Cookie usernameCookie = new Cookie("COM_HEGONGSHAN_IMOOC_USERNAME",student.getUsername());
			Cookie passwordCookie = new Cookie("COM_HEGONGSHAN_IMOOC_PASSWORD",CryptographyUtils.encryptByBase64(orignalPassword));
			usernameCookie.setPath(request.getContextPath());
			usernameCookie.setMaxAge(7*24*60*60);
			response.addCookie(usernameCookie);
			
			passwordCookie.setPath(request.getContextPath());
			passwordCookie.setMaxAge(7*24*60*60);
			response.addCookie(passwordCookie);
		}
		
		request.getSession().setAttribute("role", "student");
		return "redirect:/index";
	}
	
	@RequestMapping("/resetpwd") 
	public String forgetPassword(@RequestParam("username") String username,
								HttpServletRequest request) {
		String randomPasswordToken = "abcdefghijknmlopqrstuvwxyzABCDEFGHIJKNMLOPQRSTUVWXYZ.,><?/_-!@#$%^&*()[]{}:0123456789";
		StringBuilder randomPassword = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			randomPassword.append(randomPasswordToken.charAt(new Random().nextInt(randomPasswordToken.length())));
		}
		Student student = studentService.getStudentByUsername(username);
		student.setPassword(CryptographyUtils.encryptByMd5(randomPassword.toString(), student.getUsername()));
		studentService.doUpdate(student);
		try {
			MailUtils.sendHtmlMailForForgetPassword(mailSender, student.getUsername() , student.getNickname(), randomPassword.toString());
			request.setAttribute("tip", "邮件已发送，快去登录邮箱查看吧！");
		} catch (UnsupportedEncodingException | MessagingException e) {
			LOGGER.error("忘记密码邮件发送失败",e);
			request.setAttribute("tip", "邮件发送失败，请重新提交请求=_=");
		}
		return "/common/forget_password";
	}
	
	@RequestMapping("/update_info") 
	public String updateStudent(Student student,
								HttpServletRequest request,
								@RequestParam(value="image",required=false) MultipartFile image){
		if(image != null) {
			try {
				String relativeFilePath = FileUtils.uploadImage(image);
				student.setImageSrc(relativeFilePath);
				
			} catch (IllegalStateException | IOException e) {
				LOGGER.error("上传图片失败！",e);
			}
		}
		studentService.doUpdate(student);

		Student resultStudent = studentService.findById(student.getStudentId());
		request.getSession().setAttribute("user", resultStudent);
		return "/student/student_center_info";	
	}
	
	@RequestMapping("/center/{location}") 
	public String toCenterPage(@PathVariable String location,HttpServletRequest request ) {
		if("info".equals(location)) {
			return "/student/student_center_info";
		} else if("mycourse".equals(location)) {
			Student student = (Student) request.getSession().getAttribute("user");
			Student updatedStudent = studentService.findById(student.getStudentId());
			request.setAttribute("myCourseList", updatedStudent.getCourses());
			return "/student/student_center_mycourse";
		} else {
			return null;
		}
	}
	
	@RequestMapping("/join/{courseId}")
	public String joinCourse(@PathVariable Integer courseId,HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("user");
		Map<String,Object> ids = new HashMap<String,Object>();
		ids.put("studentId", student.getStudentId());
		ids.put("courseId", courseId);
		studentService.saveCourseToMyCourse(ids);
		Student resultStudent = studentService.findById(student.getStudentId());
		request.getSession().setAttribute("user", resultStudent);
		return "redirect:/course/detail/"+courseId;		
	}
	
	@RequestMapping("/remove/{courseId}")
	public String removeFromMyCourse(@PathVariable Integer courseId,HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("user");
		
		studentService.removeCourseFromMyCourse(student.getStudentId(), courseId);
		
		Student resultStudent = studentService.findById(student.getStudentId());
		request.getSession().setAttribute("user", resultStudent);
		return "redirect:/student/center/mycourse";	
	}
	
	@RequestMapping("/updatepwd")
	public String updatePassword(@RequestParam("password") String password,HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("user");
		String realPassword = CryptographyUtils.encryptByMd5(password, student.getUsername());
		student.setPassword(realPassword);
		studentService.doUpdate(student);
		return "redirect:/student/center/info";
	}
	
	@RequestMapping("/comment/{courseId}") 	
	public ModelAndView commentCourse(@PathVariable Integer courseId, @RequestParam(value="lessonId",required=false) Integer lessonId,
			Comment comment, ModelAndView mav, HttpSession session) {
		//1.判断是否存在该课程，若无，则不允许评论，且重定向至主页
		Course course = courseService.getCourseById(courseId);
		if(course == null) {
			mav.setViewName("redirect:/index");
			return mav;
		}	

		Student student = (Student) session.getAttribute("user");
		
		//2.判断是否为我的课程，若不是，则不允许评论，且重定向课程详情页
		//3.判断该课程是否有章节，若不是，则不允许评论，且重定向课程详情页
		boolean isMyCourse = studentService.isMyCourse(student.getStudentId(), courseId);
		if(!isMyCourse || course.getLessons() == null) {
			mav.setViewName("redirect:/course/detail/"+courseId);
			return mav;
		}
		
		//4.添加评论
		comment.setCourse(course);
		comment.setStudent(student);
		int result = commentService.saveComment(comment);
		
		/*5.判断请求链接是否存在lessonId,若存在，则重定向回原视频播放页面，
		 * 否则（即用户通过地址栏发送的评论请求)，
		 * 如：http://imooc.hegongshan.com/student/comment/29?content=134），
		 * 则重定向至课程详情页
		 */
		if(lessonId == null) {
			mav.setViewName("redirect:/course/detail/"+courseId);
		} else {
			if(result < 0 ) {
				mav.addObject("errorMsg","评论失败，请重新评论");
			}
			mav.setViewName("redirect:/course/lesson/"+lessonId);
		}
		return mav;
	}
	
	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
		
	}
}
