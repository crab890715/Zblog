package com.zblog.web.front.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.zblog.core.dal.constants.OptionConstants;
import com.zblog.core.dal.constants.PostConstants;
import com.zblog.core.dal.entity.Comment;
import com.zblog.core.dal.entity.Post;
import com.zblog.core.dal.entity.Result;
import com.zblog.core.utils.Cookier;
import com.zblog.core.utils.IdGenerator;
import com.zblog.core.utils.JsoupUtils;
import com.zblog.core.utils.ServletUtils;
import com.zblog.core.utils.StringUtils;
import com.zblog.service.CommentService;
import com.zblog.service.OptionsService;
import com.zblog.service.PostService;
import com.zblog.web.front.validator.CommentValidator;

@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private PostService postService;
	@Autowired
	private OptionsService optionsService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Object post(Comment comment, HttpServletRequest request, HttpServletResponse response) {
		Cookier cookieUtil = new Cookier(request, response);
		if (StringUtils.isBlank(comment.getCreator())) {
			comment.setCreator(cookieUtil.getCookie("comment_author"));
			comment.setUrl(cookieUtil.getCookie("comment_author_url", false));
			comment.setEmail(cookieUtil.getCookie("comment_author_email", false));
		}

		Map<String, Object> form = CommentValidator.validate(comment);
		if (!form.isEmpty()) {
			return form.put("success", false);
		}

		if (!"true".equals(optionsService.getOptionValue(OptionConstants.ALLOW_COMMENT)))
			return Result.fail("当前禁止评论");

		Post post = postService.loadById(comment.getPostid());
		if (post == null || PostConstants.COMMENT_CLOSE.equals(post.getCstatus())) {
			return Result.fail("当前禁止评论");
		}

		if (StringUtils.isBlank(comment.getParent())) {
			comment.setParent(null);
		}

		/* 根据RFC-2109中的规定，在Cookie中只能包含ASCII的编码 */
		cookieUtil.setCookie("comment_author", comment.getCreator(), "/", false, 365 * 24 * 3600, true);
		cookieUtil.setCookie("comment_author_email", comment.getEmail(), "/", false, 365 * 24 * 3600, false);
		cookieUtil.setCookie("comment_author_url", comment.getUrl(), "/", false, 365 * 24 * 3600, false);

		comment.setId(IdGenerator.uuid19());
		comment.setIp(ServletUtils.getIp(request));
		comment.setAgent(request.getHeader("User-Agent"));
		comment.setCreateTime(new Date());
		comment.setLastUpdate(comment.getCreateTime());
		/* 使用jsoup来对帖子内容进行过滤 */
		String content = HtmlUtils.htmlUnescape(comment.getContent());
		comment.setContent(JsoupUtils.simpleText(content));
		commentService.insert(comment);
		return Result.success();
	}

}
