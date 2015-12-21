package com.simpletech.MartyrMemory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.MartyrMemory.dao.base.BaseDaoImpl;
import com.simpletech.MartyrMemory.dao.FlowerDao;
import com.simpletech.MartyrMemory.model.Flower;

/**
 * 数据库表flower的Dao实现
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Repository
public class FlowerDaoImpl extends BaseDaoImpl<Flower> implements FlowerDao{

	@Override
	public int insert(Flower t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Flower t) throws Exception {
		return super.update(t);
	}

	@Override
	public int delete(Object id) throws Exception {
		return super.delete(id);
	}

	@Override
	public int countAll() throws Exception {
		return super.countAll();
	}

	@Override
	public Flower findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Flower> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Flower> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

