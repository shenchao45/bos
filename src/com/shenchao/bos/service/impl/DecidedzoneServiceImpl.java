package com.shenchao.bos.service.impl;

import com.shenchao.bos.dao.IDecidedzoneDao;
import com.shenchao.bos.dao.ISubareaDao;
import com.shenchao.bos.domain.Decidedzone;
import com.shenchao.bos.domain.Subarea;
import com.shenchao.bos.service.IDecidedzoneService;
import com.shenchao.bos.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by shenchao on 2016/11/27.
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
    @Resource
    private IDecidedzoneDao decidedzoneDao;

    @Resource
    private ISubareaDao subareaDao;

    @Override
    public void save(Decidedzone decidedzone,String[] subareaid) {
        for (String id : subareaid) {
            Subarea subarea = subareaDao.findById(id);
            subarea.setDecidedzone(decidedzone);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }
}
