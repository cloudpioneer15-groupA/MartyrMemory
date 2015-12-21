package com.simpletech.MartyrMemory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.MartyrMemory.dao.base.BaseDaoImpl;
import com.simpletech.MartyrMemory.dao.AdminDao;
import com.simpletech.MartyrMemory.model.Admin;

/**
 * 数据库表admin的Dao实现
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

	@Override
	public int insert(Admin t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Admin t) throws Exception {
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
	public Admin findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Admin> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Admin> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}

	@Override
	public List<Admin> findByNameAndPassword(String name, String password) throws Exception {
		return super.findByNameAndPassword(name, password);
	}
}

