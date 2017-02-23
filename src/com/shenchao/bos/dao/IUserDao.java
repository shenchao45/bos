package com.shenchao.bos.dao;

import com.shenchao.bos.dao.base.IBaseDao;
import com.shenchao.bos.domain.User;

/**
 * Created by  on 2016/11/23.
 */
public interface IUserDao extends IBaseDao<User>{
    public User findByUsername(String username);

}
