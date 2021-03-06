package com.zblog.biz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zblog.core.dal.constants.OptionConstants;
import com.zblog.core.dal.entity.Category;
import com.zblog.core.utils.CollectionUtils;
import com.zblog.service.CategoryService;
import com.zblog.service.OptionsService;
import com.zblog.service.PostService;

@Component
public class CategoryManager {
	@Autowired
	private PostService postService;
	@Autowired
	private OptionsService optionsService;
	@Autowired
	private CategoryService categoryService;

	/**
	 * 删除分类同时,将该分类下的文章移动到默认分类
	 * 
	 * @param cname
	 * @return
	 */
	@Transactional
	public void remove(String cname) {
		Category category = categoryService.loadByName(cname);
		List<Category> list = categoryService.loadChildren(category);
		List<String> all = new ArrayList<>(list.size() + 1);
		all.add(category.getId());
		for (Category temp : list) {
			all.add(temp.getId());
		}

		/* 先更新post的categoryid，再删除category,外键约束 */
		postService.updateCategory(all, optionsService.getOptionValue(OptionConstants.DEFAULT_CATEGORY_ID));
		categoryService.remove(category);
	}

	public List<Map<String, Object>> listAsTree() {
		List<Map<String, Object>> preOrder = categoryService.list();
		if (CollectionUtils.isEmpty(preOrder))
			return Collections.emptyList();

		/* 根据一棵树的先序遍历集合还原成一颗树 */
		Map<String, Object> root = preOrder.get(0);
		for (int i = 1; i < preOrder.size(); i++) {
			Map<String, Object> current = preOrder.get(i);
			int level = NumberUtils.toInt("" + current.get("level"));
			current.put("level", level - 1);
			Map<String, Object> parent = getLastParentByLevel(root, level - 1);
			List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
			l.add(current);
			parent.put("nodes", l);
		}

		return (List) root.get("nodes");
	}

	private static Map<String, Object> getLastParentByLevel(Map<String, Object> mc, int currentlevel) {
		Map<String, Object> current = mc;
		for (int i = 1; i < currentlevel; i++) {
			List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
			current.put("nodes", children);
			current = children.get(children.size() - 1);
		}

		return current;
	}

}
