package com.shenchao.bos.dao;

import com.shenchao.bos.dao.base.IBaseDao;
import com.shenchao.bos.domain.Staff;
import com.shenchao.bos.utils.PageBean;

/**
 * Created by shenchao on 2016/11/24.
 */
public interface IStaffDao extends IBaseDao<Staff> {
    public void pageQuery(PageBean pageBean);
}
