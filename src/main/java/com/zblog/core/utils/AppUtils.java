package com.zblog.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppUtils {
	private final static long VERSION = System.currentTimeMillis();

	public static <T> T spring(Class<T> cls) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(request().getServletContext()).getBean(cls);
	}

	public static HttpServletRequest request() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpServletResponse response() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public static String baseurl() {
		HttpServletRequest request = AppUtils.request();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}

	public static String imageurl(String uri) {
		return baseurl() + "/" + uri;
	}

	public static String version() {
		return String.valueOf(VERSION);
	}

	public static boolean isAjax() {
		String obj = request().getHeader("X-Requested-With");
		if (obj == null) {
			return false;
		}
		return "XMLHttpRequest".toUpperCase().equals(obj.toUpperCase());
	}

	/**
	 * 获取当前url
	 * 
	 * @return
	 */
	public static String url() {
		HttpServletRequest request = request();
		String query = request.getQueryString();
		return request.getRequestURL().toString() + (StringUtils.isBlank(query) ? "" : ("?" + query));
	}
}