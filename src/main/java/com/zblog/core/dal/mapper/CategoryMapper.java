package com.zblog.core.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zblog.core.dal.entity.Category;

@SuppressWarnings("unchecked")
public interface CategoryMapper extends BaseMapper{

  List<Map<String,Object>> list();

  Category loadByName(String name);

  /**
   * 获取指定分类的子分类
   * 
   * @param category
   * @return
   */
  List<Category> loadChildren(Category category);

  void updateInsertLeftv(int rightv);

  void updateInsertRightv(int rightv);

  void delete(@Param("leftv") int leftv, @Param("rightv") int rightv);

  void updateDeleteLeftv(@Param("leftv") int leftv, @Param("length") int length);

  void updateDeleteRightv(@Param("rightv") int rightv, @Param("length") int length);

}
