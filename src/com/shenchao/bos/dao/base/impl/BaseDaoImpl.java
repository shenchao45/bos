package com.shenchao.bos.dao.base.impl;

import com.shenchao.bos.dao.base.IBaseDao;
import com.shenchao.bos.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by shenchao on 2016/11/23.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    private Class<T> entityClass;

    @Resource
    public void setMySeesionFaction(SessionFactory seesionFaction) {
        setSessionFactory(seesionFaction);
    }
    /**
     * 在构造函数里面动态获得类型
     */
    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String hql = "FROM "+entityClass.getSimpleName();
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public void executeUpdate(String queryName, Object... objects) {
        Session session = getSession();//从本地线程中获得的session对象
        Query namedQuery = session.getNamedQuery(queryName);
        for(int i = 0;i<objects.length;i++) {
            namedQuery.setParameter(i, objects[i]);
        }
        namedQuery.executeUpdate();
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        //总的数据量 select count(*) from bc_staff
        //改变hibernate框架发出的sql形式
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
        pageBean.setTotal(list.get(0).intValue());//设置总的数据量
        //当前页展示的数据集合
        detachedCriteria.setProjection(null);//改回来，不发送select count(*)形式
        //重置表和类的对应映射关系
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int firstResult = (currentPage-1)*pageSize;
        int maxResult = pageSize;
        //当前这一页的数据
        List result = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResult);
        pageBean.setRows(result);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        return this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

}
