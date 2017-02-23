package com.shenchao.bos.dao;


import com.shenchao.bos.dao.base.IBaseDao;
import com.shenchao.bos.domain.Region;
import com.shenchao.bos.utils.PageBean;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region> {

	List<Region> findByQ(String q);

	void saveOrUpdate(Region region);

	void pageQuery(PageBean pageBean);
}
