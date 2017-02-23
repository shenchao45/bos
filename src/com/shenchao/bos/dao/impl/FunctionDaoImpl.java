package com.shenchao.bos.dao.impl;

import com.shenchao.bos.dao.IFunctionDao;
import com.shenchao.bos.dao.base.impl.BaseDaoImpl;
import com.shenchao.bos.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shenchao on 2016/12/3.
 */
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
    @Override
    public List<Function> findListByUserid(String id) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id = ?";
        return this.getHibernateTemplate().find(hql,id);
    }

    /**
     * 根据用户id 查询用户菜单
     * @param id
     * @return
     */
    @Override
    public List<Function> findMenuByUserId(String id) {
        String hql = "SELECT f DISTINCT FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE f.generatemenu='1' AND u.id =? ORDER BY f.zindex DESC";
        return this.getHibernateTemplate().find(hql,id);
    }

    @Override
    public List<Function> findAllMenu() {
        String hql = "FROM Function f WHERE f.generatemenu='1' ORDER BY f.zindex DESC";
        return this.getHibernateTemplate().find(hql);
    }
}
