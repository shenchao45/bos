package com.shenchao.bos.service.impl;

import com.shenchao.bos.dao.IRoleDao;
import com.shenchao.bos.dao.IUserDao;
import com.shenchao.bos.domain.Role;
import com.shenchao.bos.domain.User;
import com.shenchao.bos.service.IUserService;
import com.shenchao.bos.utils.MD5Utils;
import com.shenchao.bos.utils.PageBean;
import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shenchao on 2016/11/23.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private IRoleDao roleDao;

    @Override
    public void resetPassword(User u) {
        u.setPassword(MD5Utils.md5(u.getPassword()));
        userDao.executeUpdate("editPassword", u.getPassword(), u.getId());
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        userDao.pageQuery(pageBean);
    }

    @Override
    public void add(User model,String[] roleIds) {
        model.setPassword(MD5Utils.md5(model.getPassword()));
        userDao.save(model);
        org.activiti.engine.identity.User user = new UserEntity(model.getId());
        identityService.saveUser(user);
        if (roleIds != null&&roleIds.length>0) {
            for (String id : roleIds) {
                Role role = roleDao.findById(id);
                model.getRoles().add(role);
                identityService.createMembership(user.getId(),role.getName());
            }
        }
    }
}
