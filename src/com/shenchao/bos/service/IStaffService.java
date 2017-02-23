package com.shenchao.bos.service;

import com.shenchao.bos.domain.Staff;
import com.shenchao.bos.utils.PageBean;

import java.util.List;

/**
 * Created by shenchao on 2016/11/24.
 */
public interface IStaffService {

    void save(Staff model);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    Staff findById(String id);

    void update(Staff model);

    List<Staff> findListNotDel();
}
