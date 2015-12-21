package com.simpletech.MartyrMemory.service.impl;

import com.simpletech.MartyrMemory.dao.AdminDao;
import com.simpletech.MartyrMemory.model.Admin;
import com.simpletech.MartyrMemory.model.base.ModelBase;
import com.simpletech.MartyrMemory.service.AdminService;
import com.simpletech.MartyrMemory.service.base.BaseServiceImpl;
import com.simpletech.MartyrMemory.util.Page;
import com.simpletech.MartyrMemory.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表admin的Service接实现
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{

	@Autowired
	AdminDao dao;
	
	@Override
	public int insert(Admin model) throws Exception{
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Admin model) throws Exception {
		Admin old = findById(getModelID(model));
		if (old == null) {
			throw new ServiceException("请求更新记录不存在或已经被删除！");
		}
		model = checkNullField(old, model);
		return dao.update(model);
	}

	@Override
	public int delete(Object id) throws Exception {
		return dao.delete(id);
	}

	@Override
	public Admin findById(Object id) throws Exception{
		return dao.findById(id);
	}

	@Override
	public List<Admin> findAll() throws Exception{
		return dao.findAll();
	}

	@Override
	public int delete(String id) throws Exception{
		return dao.delete(id);
	}

	@Override
	public List<Admin> findByPage(int limit, int start) throws Exception {
		return dao.findByPage(limit, start);
	}



	@Override
	public Admin findById(String id) throws Exception {
		return dao.findById(id);
	}
	
	@Override
	public Page<Admin> listByPage(int pageSize, int pageNo) throws Exception{
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Admin> list = dao.findByPage(limit, start);
		
		return new Page<Admin>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() throws Exception {
		return dao.countAll();
	}

	@Override
	public List<Admin> findByNameAndPassword(String name, String password) throws  Exception {

		return dao.findByNameAndPassword(name, password);
	}
}
