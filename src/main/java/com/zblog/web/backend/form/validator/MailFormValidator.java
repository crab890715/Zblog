package com.zblog.web.backend.form.validator;

import java.util.HashMap;
import java.util.Map;

import com.zblog.core.utils.StringUtils;
import com.zblog.web.backend.form.MailOption;

public class MailFormValidator {

	public static Map<String, Object> validate(MailOption form) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isBlank(form.getHost())) {
			result.put("host", "请输入host");
		}

		if (form.getPort() < 10) {
			result.put("port", "请输入合法端口号");
		}

		if (StringUtils.isBlank(form.getUsername())) {
			result.put("username", "请输入用户名");
		}

		if (StringUtils.isBlank(form.getPassword())) {
			result.put("password", "请输入密码");
		}

		return result;
	}
}
