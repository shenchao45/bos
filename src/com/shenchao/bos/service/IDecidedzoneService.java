package com.shenchao.bos.service;

import com.shenchao.bos.domain.Decidedzone;
import com.shenchao.bos.utils.PageBean;

/**
 * Created by shenchao on 2016/11/27.
 */
public interface IDecidedzoneService {
    void save(Decidedzone decidedzone,String[] subareaid);

    void pageQuery(PageBean pageBean);
}
