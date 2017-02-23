package com.shenchao.bos.service.impl;

import com.shenchao.bos.dao.IRoleDao;
import com.shenchao.bos.domain.Function;
import com.shenchao.bos.domain.Role;
import com.shenchao.bos.service.IRoleSerivce;
import com.shenchao.bos.utils.PageBean;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shenchao on 2016/12/3.
 */
@Transactional
@Service
public class RoleServiceImpl implements IRoleSerivce {
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IdentityService identityService;
    @Override
    public void add(Role role, String ids) {
        Group group = new GroupEntity(role.getName());
        identityService.saveGroup(group);
        roleDao.save(role);
        String[] split = ids.split(",");
        for (String id : split) {
            role.getFunctions().add(new Function(id));
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        roleDao.pageQuery(pageBean);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
