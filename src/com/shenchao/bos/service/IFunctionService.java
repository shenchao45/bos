package com.shenchao.bos.service;

import com.shenchao.bos.domain.Function;
import com.shenchao.bos.utils.PageBean;

import java.util.List;

/**
 * Created by shenchao on 2016/12/3.
 */
public interface IFunctionService {
    public void pageQuery(PageBean pageBean);

    List<Function> findMenu();
    void add(Function function);

    List<Function> findAll();


}
