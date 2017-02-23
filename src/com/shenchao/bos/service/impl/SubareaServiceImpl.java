package com.shenchao.bos.service.impl;

import com.shenchao.bos.dao.ISubareaDao;
import com.shenchao.bos.domain.Subarea;
import com.shenchao.bos.service.ISubareaService;
import com.shenchao.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shenchao on 2016/11/26.
 */
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
    @Resource
    private ISubareaDao subareaDao;
    @Override
    public void add(Subarea entity) {
        subareaDao.save(entity);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }

    @Override
    public List<Subarea> findListNotAssociation() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.isNull("decidedzone"));
        return subareaDao.findByCriteria(detachedCriteria);
    }
}
