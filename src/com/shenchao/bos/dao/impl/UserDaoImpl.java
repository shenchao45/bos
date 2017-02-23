package com.shenchao.bos.dao.impl;

import com.shenchao.bos.dao.IUserDao;
import com.shenchao.bos.dao.base.impl.BaseDaoImpl;
import com.shenchao.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shenchao on 2016/11/23.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
    public User findByUsername(String username) {
        List<User> list = this.getHibernateTemplate().find("from User u where u.username=?",username);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
