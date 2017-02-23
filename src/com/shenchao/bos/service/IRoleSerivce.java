package com.shenchao.bos.service;

import com.shenchao.bos.domain.Role;
import com.shenchao.bos.utils.PageBean;

import java.util.List;

/**
 * Created by shenchao on 2016/12/3.
 */
public interface IRoleSerivce {
    public void add(Role role, String ids);

    void pageQuery(PageBean pageBean);

    List<Role> findAll();
}
