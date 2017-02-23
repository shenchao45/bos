package com.shenchao.bos.service;

import com.shenchao.bos.domain.Subarea;
import com.shenchao.bos.utils.PageBean;

import java.util.List;

/**
 * Created by shenchao on 2016/11/26.
 */
public interface ISubareaService {
    void add(Subarea entity);

    void pageQuery(PageBean pageBean);

    List<Subarea> findAll();

    List<Subarea> findListNotAssociation();
}
