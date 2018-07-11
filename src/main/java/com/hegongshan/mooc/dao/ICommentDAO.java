package com.hegongshan.mooc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;

import com.hegongshan.mooc.entity.Comment;

@Resource
public interface ICommentDAO {
	
	Integer saveComment(Comment comment) throws Exception;
	
	Integer removeCommentById(Integer commentId) throws Exception;
	
	Integer removeCommentBatch(Integer[] commentIds) throws Exception;
	
	List<Comment> listAllCommentByPage(@Param("startRow") Integer startRow,@Param("pageSize") Integer pageSize) throws Exception;
	
	List<Comment> listCommentByCourseId(Integer courseId) throws Exception;
	
	Integer countAllComment() throws Exception;
}
