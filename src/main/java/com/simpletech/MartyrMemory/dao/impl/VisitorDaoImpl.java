package com.simpletech.MartyrMemory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.MartyrMemory.dao.base.BaseDaoImpl;
import com.simpletech.MartyrMemory.dao.VisitorDao;
import com.simpletech.MartyrMemory.model.Visitor;

/**
 * 数据库表visitor的Dao实现
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Repository
public class VisitorDaoImpl extends BaseDaoImpl<Visitor> implements VisitorDao{

	@Override
	public int insert(Visitor t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Visitor t) throws Exception {
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
	public Visitor findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Visitor> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Visitor> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

