package com.hegongshan.mooc.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hegongshan.mooc.entity.Comment;
import com.hegongshan.mooc.service.ICommentService;
import com.hegongshan.mooc.util.PageUtils;
import com.hegongshan.mooc.util.StringUtils;

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
	
	@Resource
	private ICommentService commentService;
	
	@RequestMapping("/list")
	public ModelAndView listComment(@RequestParam(value="page",required=false) Integer page, ModelAndView mav) {
		
		PageUtils<Comment> pager = new PageUtils<Comment>();
		pager.setCurrentPage(page);
		pager.setTotalRows(commentService.countAllComment());
		pager.setPageSize(10);
		
		List<Comment> commentList = commentService.listAllCommentByPage(pager);
		pager.setList(commentList);
		
		mav.addObject("pager", pager);
		mav.addObject("mainPage", "/WEB-INF/jsp/admin/comment/comment_list.jsp");
		mav.setViewName("/admin/index");
		return mav;
	}
	
	@RequestMapping("/remove/{commentId}")
	public ModelAndView removeCommentById(@PathVariable Integer commentId,ModelAndView mav) {
		int result = commentService.removeCommentById(commentId);
		if(result <= 0) {
			mav.addObject("errorMsg", "删除失败，请重新尝试一下=_="); 
			mav.setViewName("forward:/admin/comment/list");
		} else {
			mav.setViewName("redirect:/admin/comment/list");
		}
		return mav;
	}
	
	@RequestMapping("/remove/batch")
	public ModelAndView removeCommentBatch(@RequestParam("commentids") String commentIds,ModelAndView mav) {
		int result = commentService.removeCommentBatch(StringUtils.toIntegerArrayBySplit(commentIds, ","));
		if(result <= 0) {
			mav.addObject("errorMsg", "删除失败，请重新尝试一下=_=");
			mav.setViewName("forward:/admin/comment/list");
		} else {
			mav.setViewName("redirect:/admin/comment/list");
		}
		return mav;
	}
}
