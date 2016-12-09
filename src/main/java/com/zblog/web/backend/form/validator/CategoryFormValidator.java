package com.zblog.web.backend.form.validator;

import java.util.HashMap;
import java.util.Map;

import com.zblog.core.dal.entity.Category;
import com.zblog.core.utils.StringUtils;

public class CategoryFormValidator {

	public static Map<String, Object> validateInsert(Category category) {
		Map<String, Object> form = new HashMap<String, Object>();
		if (StringUtils.isBlank(category.getName())) {
			form.put("msg", "分类名称不能为空");
		}

		return form;
	}

}
