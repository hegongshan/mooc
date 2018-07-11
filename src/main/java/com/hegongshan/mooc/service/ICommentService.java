package com.hegongshan.mooc.service;

import java.util.List;

import com.hegongshan.mooc.entity.Comment;
import com.hegongshan.mooc.util.PageUtils;

public interface ICommentService {
	Integer saveComment(Comment comment);

	Integer removeCommentById(Integer commentId);
	
	Integer removeCommentBatch(Integer[] commentIds);
	
	List<Comment> listAllCommentByPage(PageUtils<Comment> pager);
	
	Integer countAllComment();
}
