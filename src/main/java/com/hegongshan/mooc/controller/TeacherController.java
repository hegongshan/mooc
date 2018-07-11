package com.hegongshan.mooc.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
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

import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.entity.CourseType;
import com.hegongshan.mooc.entity.Lesson;
import com.hegongshan.mooc.entity.Teacher;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.ICourseTypeService;
import com.hegongshan.mooc.service.ILessonService;
import com.hegongshan.mooc.service.IStudentService;
import com.hegongshan.mooc.service.ITeacherService;
import com.hegongshan.mooc.util.CryptographyUtils;
import com.hegongshan.mooc.util.DateUtils;
import com.hegongshan.mooc.util.FileUtils;
import com.hegongshan.mooc.util.MailUtils;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	private static Logger logger = LoggerFactory.getLogger(TeacherController.class);
	
	@Resource
	private ITeacherService teacherService;
	
	@Resource
	private ICourseService courseService;
	
	@Resource
	private ICourseTypeService courseTypeService;
		
	@Resource
	private ILessonService lessonService;
	
	@Resource
	private IStudentService studentService;	
	@Resource
	private JavaMailSenderImpl mailSender;
	
	@RequestMapping("/register")
	public String register(Teacher teacher,
						   @RequestParam(value="uAuthCode",required=false) String uAuthCode,
			               HttpServletRequest request) {
		
		if(uAuthCode == null) {
			return "/teacher/teacher_register";
		}
		
		String sAuthCode = (String) request.getSession().getAttribute("sAuthCode");
		if(!sAuthCode.equalsIgnoreCase(uAuthCode)) {
			request.setAttribute("teacher", teacher);
			request.setAttribute("errorMsg", "验证码错误！");
			return "/teacher/teacher_register";
		}
		
		int result = teacherService.saveTeacher(teacher,request.getContextPath());
		if(result > 0) {		
			return "/common/register_confirm";
		}
		return "503";
	}
	
	@RequestMapping(value="/confirm",method=RequestMethod.GET)
	public String confirm(@RequestParam("username") String username,
						  @RequestParam("activeCode") String activeCode,
						  HttpServletRequest request) {
		Teacher teacher = teacherService.getTeacherByUsername(username);
		
		//1.验证用户是否存在
		if(teacher == null) {
			request.setAttribute("errorMsg", "用户不存在！");
			return "/common/register_fail";
		}
		
		Date date = new Date();
		//2.验证激活码是否过期
		if(!date.before(DateUtils.operateDate(teacher.getRegisterTime(), 1))) {
			//验证码过期，删除注册信息
			teacherService.removeTeacherById(teacher.getTeacherId());
			request.setAttribute("errorMsg", "激活码已过期，请重新注册！");
			return "/common/register_fail";
		}
		
		//3.验证激活码是否正确
		if(!teacher.getActiveCode().equals(activeCode)) {
			request.setAttribute("errorMsg", "激活码不正确！");
			return "/common/register_fail";			
		} 
		
		//4.激活
		teacher.setStatus(1);
		int result  = teacherService.updateTeacher(teacher);
		if(result < 0) {
			logger.error("教师激活失败");
			return "503";
		}
		request.getSession().setAttribute("user", teacher);
		request.getSession().setAttribute("role", "teacher");
		return "redirect:/index";
	}
	
	@RequestMapping("/login")
	public String login(Teacher teacher,
						@RequestParam(value="rememberMe",required=false) String rememberMe,
						HttpServletRequest request,
						HttpServletResponse response) {
		String orignalPassword = teacher.getPassword();
		Teacher currentTeacher = teacherService.getTeacherByUsernameAndPassword(teacher);
		if(currentTeacher == null) {
			request.setAttribute("user", teacher);
			request.setAttribute("errorMsg", "用户名或密码错误");
			return "/common/login";
		}
		if(currentTeacher.getStatus() == 0) {
			request.setAttribute("errorMsg", "该账户尚未激活，请登录邮箱激活");
			return "/common/login";
		}
		if(currentTeacher.getStatus() == 2) {
			request.setAttribute("errorMsg", "该账户已被管理员封禁，请联系管理员解封");
			return "/common/login";
		}
		
		if(rememberMe != null &&"remember-me".equals(rememberMe)) {
			Cookie usernameCookie = new Cookie("COM_HEGONGSHAN_IMOOC_USERNAME",teacher.getUsername());
			Cookie passwordCookie = new Cookie("COM_HEGONGSHAN_IMOOC_PASSWORD",CryptographyUtils.encryptByBase64(orignalPassword));
			usernameCookie.setPath(request.getContextPath());
			usernameCookie.setMaxAge(7*24*60*60);
			response.addCookie(usernameCookie);
			
			passwordCookie.setPath(request.getContextPath());
			passwordCookie.setMaxAge(7*24*60*60);
			response.addCookie(passwordCookie);
		}
		
		request.getSession().setAttribute("role", "teacher");
		request.getSession().setAttribute("user", currentTeacher);
		return "redirect:/index";
	}
	
	@RequestMapping("/center/{location}") 
	public ModelAndView toCenterPage(@PathVariable String location,
			HttpServletRequest request,ModelAndView mv) {

		Teacher teacher = (Teacher) request.getSession().getAttribute("user");

		List<CourseType> courseTypeList = courseTypeService.listAllCourseType(); 
		if("info".equals(location)) {
			mv.setViewName("/teacher/teacher_center_info");
		} else if("offer_course".equals(location)) {
			Teacher resultTeacher = teacherService.getTeacherById(teacher.getTeacherId());
			request.getSession().setAttribute("user",resultTeacher);
			request.setAttribute("courseList", resultTeacher.getCourses());
			request.setAttribute("courseTypeList", courseTypeList);
			mv.setViewName("/teacher/teacher_center_list_course");
		} else if("manage_course".equals(location)){
			Teacher resultTeacher = teacherService.getTeacherById(teacher.getTeacherId());
			request.getSession().setAttribute("user",resultTeacher);
			request.setAttribute("courseList", resultTeacher.getCourses());
			request.setAttribute("courseTypeList", courseTypeList);
			mv.setViewName("/teacher/teacher_center_manage_course");
		} else if("lesson".equals(location)) {
			Course currentCourse = teacher.getCourses().get(0);
			request.setAttribute("currentCourse",currentCourse);
			mv.setViewName("/teacher/teacher_center_manage_lesson");
		}
		return mv;
	}
	
	@RequestMapping("/remove/{courseId}")
	public String removeFromMyOfferCourse(@PathVariable Integer courseId,HttpServletRequest request) {

		if(courseService.getCourseById(courseId).getStudentNumber() > 0) {
			request.setAttribute("errorMsg", "有学生正在学习该课程，不能删除");
			return "forward:/teacher/center/manage_course";
		}
		if(lessonService.listLessonByCourseId(courseId).size() > 0) {
			request.setAttribute("errorMsg", "该课程下有章节，不能删除");
			return "forward:/teacher/center/manage_course";
		}
		Teacher teacher = (Teacher) request.getSession().getAttribute("user");
		
		courseService.removeCourseById(courseId);
		Teacher resultTeacher = teacherService.getTeacherById(teacher.getTeacherId());
		request.getSession().setAttribute("user",resultTeacher);
		return "redirect:/teacher/center/manage_course";
	}
	
	@RequestMapping("/add_course") 
	public ModelAndView addCourse(@RequestParam("courseName") String courseName,
							@RequestParam("courseTypeId") Integer courseTypeId,
							@RequestParam("image") MultipartFile image,
							@RequestParam("description") String description,
							ModelAndView mav ,HttpSession session) {
		Course course = new Course();
		CourseType courseType = new CourseType();
		courseType.setCourseTypeId(courseTypeId);
		course.setCourseName(courseName);
		course.setCourseType(courseType);
		course.setDescription(description);
		course.setTeacher((Teacher)session.getAttribute("user"));
		
		String relativeFilePath = null;
		try {
			relativeFilePath = FileUtils.uploadImage(image);
			course.setCoverImagePath(relativeFilePath);
			
		} catch (IllegalStateException | IOException e) {
			logger.error("封面图片上传失败 ！",e);
			mav.addObject("errorMsg","封面图片上传失败");
		}
		courseService.saveCourse(course);
		
		mav.setViewName("redirect:/teacher/center/offer_course");
		return mav;
	}
	
	@RequestMapping("/update_info") 
	public String updateTeacher(Teacher teacher,
			                    @RequestParam(value="image",required=false) MultipartFile image,
								HttpServletRequest request){
		
		if(image != null) {
			String filePrefix = request.getServletContext().getRealPath("/");
			String fileName = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS");
			String fileSuffix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
			String absoluteFilePath = filePrefix + "static" + File.separator 
					+ "images" + File.separator + "teacher"+ File.separator 
					+  fileName	+ fileSuffix;
			String relativeFilePath = request.getContextPath() +"/static/images/teacher/" 
					+ fileName + fileSuffix;
			try {
				image.transferTo(new File(absoluteFilePath));
				teacher.setImageSrc(relativeFilePath);
			} catch (IllegalStateException | IOException e) {
				logger.error("上传图片失败！",e);
			}
		}
		teacherService.updateTeacher(teacher);

		Teacher resultTeacher = teacherService.getTeacherById(teacher.getTeacherId());
		
		request.getSession().setAttribute("user", resultTeacher);
		return "/teacher/teacher_center_info";	
		
	}
	
	@RequestMapping("/upload/course/resource") 
	public String uploadCourseResource(@RequestParam(value="courseId") Integer courseId,
									   @RequestParam(value="courseResource") MultipartFile courseResource,
									   HttpServletRequest request){
		
		Course course = new Course();
		course.setCourseId(courseId);
		try {
			String url = FileUtils.uploadResource(courseResource);
			course.setResourceSrc(url);
			courseService.updateCourse(course);
			request.setAttribute("resource_message", "上传成功");
		} catch (IllegalStateException | IOException e) {
			logger.error("课程资源上传失败",e);
			request.setAttribute("resource_message", "上传失败");
		}
		return "forward:/teacher/center/manage_course";
	}
	
	@RequestMapping("/course/edit") 
	public String logout(@RequestParam("courseTypeId") Integer courseTypeId,
			Course course,HttpServletRequest request) {
		
		CourseType courseType = new CourseType();
		courseType.setCourseTypeId(courseTypeId);
		
		course.setCourseType(courseType);
		
		int result = courseService.updateCourse(course);
		if(result <= 0) {
			request.setAttribute("errorMsg", "更新失败");
		}
		return  "forward:/teacher/center/manage_course";
	}
	
	@RequestMapping("/lesson/list") 
	public ModelAndView listLessonByCourseId(@RequestParam("courseId") Integer courseId,ModelAndView mav) {
		Course course = courseService.getCourseById(courseId);
		mav.addObject("currentCourse", course);
		mav.setViewName("/teacher/teacher_center_manage_lesson");
		return mav;
	}
	
	@RequestMapping("/lesson/save") 
	public ModelAndView addlesson(@RequestParam("courseId") Integer courseId,
			@RequestParam("video") MultipartFile video, Lesson lesson,ModelAndView mav) {

		Course course = courseService.getCourseById(courseId);
		lesson.setCourse(course);
		try {
			String url = FileUtils.uploadVideo(video);
			lesson.setUrl(url);
			lessonService.saveLesson(lesson);
			mav.setViewName("redirect:/teacher/lesson/list?courseId="+courseId);
		} catch (IOException e) {
			logger.error("视频上传失败",e);
			mav.addObject("tip", "上传失败");
			mav.setViewName("forward:/teacher/lesson/list?courseId="+courseId);
		}
		return mav;
	}
	
	@RequestMapping("/lesson/update") 
	public ModelAndView updateLesson(@RequestParam(value="video",required=false) MultipartFile video, Lesson lesson,ModelAndView mav) {
		int courseId = lessonService.getLessonById(lesson.getLessonId()).getCourse().getCourseId();
		if(!video.isEmpty()) {
			String url;
			try {
				url = FileUtils.uploadVideo(video);
				lesson.setUrl(url);
			} catch (IOException e) {
				logger.error("视频上传失败",e);
				mav.addObject("tip", "上传失败");
				mav.setViewName("forward:/teacher/lesson/list?courseId="+courseId);
				return mav;
			}
		}
		lessonService.updateLesson(lesson);
		mav.setViewName("redirect:/teacher/lesson/list?courseId="+courseId);
		return mav;
	}
	
	@RequestMapping("/lesson/remove/{lessonId}")
	public ModelAndView removeLesson(@PathVariable Integer lessonId,ModelAndView mav) {
		int courseId = lessonService.getLessonById(lessonId).getCourse().getCourseId();
		lessonService.removeLessonById(lessonId);
		mav.setViewName("redirect:/teacher/lesson/list?courseId="+courseId);
		return mav;
	}
	
	@RequestMapping("/updatepwd")
	public String updatePassword(@RequestParam("password") String password,HttpServletRequest request) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("user");
		teacher.setPassword(CryptographyUtils.encryptByMd5(password, teacher.getUsername()));
		teacherService.updateTeacher(teacher);
		return "redirect:/teacher/center/info";
	}
	
	@RequestMapping("/resetpwd") 
	public String forgetPassword(@RequestParam("username") String username,
								HttpServletRequest request) {
		String randomPasswordToken = "abcdefghijknmlopqrstuvwxyzABCDEFGHIJKNMLOPQRSTUVWXYZ.,><?/_-!@#$%^&*()[]{}:0123456789";
		StringBuilder randomPassword = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			randomPassword.append(randomPasswordToken.charAt(new Random().nextInt(randomPasswordToken.length())));
		}
		Teacher teacher = teacherService.getTeacherByUsername(username);
		teacher.setPassword(CryptographyUtils.encryptByMd5(randomPassword.toString(), teacher.getUsername()));
		teacherService.updateTeacher(teacher);
		try {
			MailUtils.sendHtmlMailForForgetPassword(mailSender, teacher.getUsername() , teacher.getNickname(), randomPassword.toString());
			request.setAttribute("tip", "邮件已发送，快去登录邮箱查看吧！");
		} catch (UnsupportedEncodingException | MessagingException e) {
			logger.error("忘记密码邮件发送失败",e);
			request.setAttribute("tip", "邮件发送失败，请重新提交请求=_=");
		}
		return "/common/resetpwd";
	}
	
	public String checkUsername(@RequestParam("username") String username,HttpServletResponse response) throws IOException {
		response.setDateHeader("Expires",-1);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		
		Teacher teacher = teacherService.getTeacherByUsername(username);
		PrintWriter out = response.getWriter();

		if(teacher != null) {
			out.write("isExist");
		} else {
			out.write("isNotExist");
		}
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
}
