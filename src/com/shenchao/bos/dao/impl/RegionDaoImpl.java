package com.shenchao.bos.dao.impl;


import com.shenchao.bos.dao.IRegionDao;
import com.shenchao.bos.dao.base.impl.BaseDaoImpl;
import com.shenchao.bos.domain.Region;
import com.shenchao.bos.utils.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	public List<Region> findByQ(String q) {
		String hql = "FROM Region WHERE province LIKE ? OR city LIKE ? OR district LIKE ?";
		return this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%");
	}

	@Override
	public void saveOrUpdate(Region region) {
		this.getHibernateTemplate().saveOrUpdate(region);
	}


}
