package com.shenchao.bos.service.impl;

import com.shenchao.bos.dao.IStaffDao;
import com.shenchao.bos.domain.Staff;
import com.shenchao.bos.service.IStaffService;
import com.shenchao.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shenchao on 2016/11/24.
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Resource
    private IStaffDao staffDao;

    @Override
    public void save(Staff model) {
        staffDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    @Override
    public void deleteBatch(String ids) {
        String[] staffIds = ids.split(",");
        for (String staffid : staffIds) {
            staffDao.executeUpdate("staff.delete",staffid);
        }
    }

    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public void update(Staff model) {
        staffDao.update(model);
    }

    @Override
    public List<Staff> findListNotDel() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.eq("deltag", "0"));
        return staffDao.findByCriteria(detachedCriteria);
    }
}
