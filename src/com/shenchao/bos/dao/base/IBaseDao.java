package com.shenchao.bos.dao.base;

import com.shenchao.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * 抽取持久层通用方法
 * Created by shenchao on 2016/11/23.
 */
public interface IBaseDao<T> {
    void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Serializable id);

    List<T> findAll();

    void executeUpdate(String queryName, Object... objects);

    void pageQuery(PageBean pageBean);

    List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
