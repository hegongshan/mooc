package com.hegongshan.mooc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hegongshan.mooc.dao.ICommentDAO;
import com.hegongshan.mooc.entity.Comment;
import com.hegongshan.mooc.service.ICommentService;
import com.hegongshan.mooc.util.PageUtils;

@Service
public class CommentServiceImpl implements ICommentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

	@Resource
	private ICommentDAO commentDAO;
	
	@Override
	public Integer saveComment(Comment comment) {
		try {
			return commentDAO.saveComment(comment);
		} catch (Exception e) {
			LOGGER.error("插入数据到t_comment失败",e);
			return -1;
		}
	}

	@Override
	public Integer removeCommentById(Integer commentId) {
		try {
			return commentDAO.removeCommentById(commentId);
		} catch (Exception e) {
			LOGGER.error("从t_comment中删除数据失败",e);
			return -1;
		}
	}
	
	@Override
	public Integer removeCommentBatch(Integer[] commentIds) {
		try {
			return commentDAO.removeCommentBatch(commentIds);
		} catch (Exception e) {
			LOGGER.error("从t_comment中批量删除数据失败", e);
			return -1;
		}
	}

	@Override
	public List<Comment> listAllCommentByPage(PageUtils<Comment> pager) {
		try {
			return commentDAO.listAllCommentByPage(pager.getStartPage(),pager.getPageSize());
		} catch (Exception e) {
			LOGGER.error("从t_comment中查询数据失败",e);
			return null;
		}
	}

	@Override
	public Integer countAllComment() {
		try {
			return commentDAO.countAllComment();
		} catch (Exception e) {
			LOGGER.error("从t_comment中查询数据失败",e);
			return null;
		}
	}

}
