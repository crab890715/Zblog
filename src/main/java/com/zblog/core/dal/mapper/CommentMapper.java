package com.zblog.core.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zblog.service.vo.CommentVO;

public interface CommentMapper extends BaseMapper {

	List<CommentVO> listRecent();

	List<Map<String, Object>> listCountByGroupStatus();

	/**
	 * 根据postid获取被批准的评论和指定creator的评论
	 * 
	 * @param postid
	 * @param creator
	 * @return
	 */
	List<CommentVO> listByPost(@Param("postid") String postid, @Param("creator") String creator);

	/**
	 * 更改comment的状态
	 * 
	 * @param commentid
	 */
	int setStatus(@Param("commentid") String commentid, @Param("status") String status);

}
