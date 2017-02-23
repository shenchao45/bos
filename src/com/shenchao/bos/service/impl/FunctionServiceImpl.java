package com.shenchao.bos.service.impl;

import com.shenchao.bos.dao.IFunctionDao;
import com.shenchao.bos.domain.Function;
import com.shenchao.bos.domain.User;
import com.shenchao.bos.service.IFunctionService;
import com.shenchao.bos.utils.BosContext;
import com.shenchao.bos.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shenchao on 2016/12/3.
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
    @Resource
    private IFunctionDao functionDao;
    @Override
    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
    }

    @Override
    public List<Function> findMenu() {
        User login = BosContext.getLogin();
        List<Function> list = null;
//        if (login.getUsername().equals("admin")) {
            list = functionDao.findAllMenu();
//        }else{
//            list = functionDao.findMenuByUserId(login.getId());
//        }
        return list;
    }

    @Override
    public void add(Function function) {
        Function function1 = function.getFunction();
        if (function1 != null && function1.getId().equals("")) {
            function.setFunction(null);
        }
        functionDao.save(function);
    }

    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }
}
