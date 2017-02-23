package com.shenchao.bos.service;

import com.shenchao.bos.domain.Region;
import com.shenchao.bos.utils.PageBean;

import java.util.List;


public interface IRegionService {

	public void saveBatch(List<Region> list);

	public void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findByQ(String q);

}
