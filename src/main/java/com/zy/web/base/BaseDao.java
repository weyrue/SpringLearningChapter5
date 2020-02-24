package com.zy.web.base;

import com.zy.mysql.mybatis.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {
    int insert(T entity);

    int updateByPrimaryKeySelective(T entity);

    T get(@Param("id") Long id);

    T get(T entity);

    List<T> findByList(T entity);

    List<T> findBypage(Page<T> page, T entity);
}
