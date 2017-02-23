package com.shenchao.bos.service.impl;


import com.shenchao.bos.dao.IRegionDao;
import com.shenchao.bos.domain.Region;
import com.shenchao.bos.service.IRegionService;
import com.shenchao.bos.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	@Resource
	private IRegionDao regionDao;

	public void saveBatch(List<Region> list) {
		for (Region region : list) {
			regionDao.saveOrUpdate(region);
		}
	}

	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	public List<Region> findAll() {
		return regionDao.findAll();
	}

	public List<Region> findByQ(String q) {
		return regionDao.findByQ(q);
	}
}
