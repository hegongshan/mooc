package com.hegongshan.mooc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Course;
import com.hegongshan.mooc.entity.CourseType;
import com.hegongshan.mooc.entity.Lesson;
import com.hegongshan.mooc.entity.Student;
import com.hegongshan.mooc.entity.Teacher;
import com.hegongshan.mooc.service.ICourseService;
import com.hegongshan.mooc.service.ICourseTypeService;
import com.hegongshan.mooc.service.ILessonService;
import com.hegongshan.mooc.util.PageUtils;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Resource
	private ICourseService courseService;
	
	@Resource
	private ICourseTypeService courseTypeService;
	
	@Resource
	private ILessonService lessonService;
	
	@RequestMapping("/detail/{courseId}")
	public String courseDetail(@PathVariable Integer courseId,HttpServletRequest request) {

		Course course = courseService.getCourseById(courseId);
		if(course == null) {
			return "redirect:/index";
		}
		
		Object user = request.getSession().getAttribute("user");
		if(user != null) {
			if(user instanceof Student) {
				Student student = (Student) user;
				for(Course c : student.getCourses()) {
					if(c.getCourseId().equals(courseId)) {
						request.setAttribute("isMyCourse", true);
					}
				}
			}
			if(user instanceof Teacher) {
				Teacher teacher = (Teacher) user;
				for(Course c : teacher.getCourses()) {
					if(c.getCourseId().equals(courseId)) {
						request.setAttribute("isMyCourse", true);
					}
				}
			}
		} else {
			request.setAttribute("isMyCourse", false);
		}
		
		List<Lesson> lessonList = lessonService.listLessonByCourseId(courseId);
		
		request.setAttribute("course", course);
		request.setAttribute("lessonList", lessonList);
		return "/common/course_detail";
	}
	
	@RequestMapping("/all")
	public ModelAndView listAllCourse(@RequestParam(value="page",required=false) Integer page,
									  ModelAndView mv) {
		List<CourseType> courseTypeList = courseTypeService.listAllCourseType();
		
		PageUtils<Course> pager = new PageUtils<Course>();
		pager.setCurrentPage(page);
		pager.setTotalRows(courseService.countAllCourse());
		pager.setPageSize(4);
		List<Course> courseList = 
				courseService.listCourseByPage(pager.getStartPage(),pager.getPageSize());
		
		mv.addObject("courseTypeList", courseTypeList);
		pager.setList(courseList);
		mv.addObject("pager",pager);
		mv.setViewName("/common/course_list");
		return mv;
	}
	
	@RequestMapping("/lesson/{lessonId}")
	public String videoPlayer(@PathVariable Integer lessonId,HttpServletRequest request) {
		Lesson currentLesson = lessonService.getLessonById(lessonId);
		
		/*Object user = request.getSession().getAttribute("user");
		if(user != null && user instanceof Student) {
			Student currentStudent = (Student) user;
			if(!currentStudent.getCourses().contains(currentLesson.getCourse())) {
				return "redirect:/course/detail/"+currentLesson.getCourse().getCourseId();
			}
		}*/		
		
		List<Lesson> lessonList = lessonService.listLessonByCourseId(currentLesson.getCourse().getCourseId());
		
		request.setAttribute("currentLesson", currentLesson);
		request.setAttribute("lessonList", lessonList);
		return "/common/lesson_player";
	}
}
