package com.shenchao.bos.dao;

import com.shenchao.bos.dao.base.IBaseDao;
import com.shenchao.bos.domain.Function;

import java.util.List;

/**
 * Created by shenchao on 2016/12/3.
 */
public interface IFunctionDao extends IBaseDao<Function> {
    List<Function> findListByUserid(String id);

    List<Function> findMenuByUserId(String id);

    List<Function> findAllMenu();
}
