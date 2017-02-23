package com.shenchao.bos.service;

import com.shenchao.bos.domain.User;
import com.shenchao.bos.utils.PageBean;

/**
 * Created by shenchao on 2016/11/23.
 */
public interface IUserService {
    void resetPassword(User u);

    void pageQuery(PageBean pageBean);

    void add(User model,String[] roleIds);
}
