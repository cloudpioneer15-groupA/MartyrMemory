package com.simpletech.MartyrMemory.service.impl;

import com.simpletech.MartyrMemory.dao.MessageDao;
import com.simpletech.MartyrMemory.model.Message;
import com.simpletech.MartyrMemory.model.base.ModelBase;
import com.simpletech.MartyrMemory.service.MessageService;
import com.simpletech.MartyrMemory.service.base.BaseServiceImpl;
import com.simpletech.MartyrMemory.util.Page;
import com.simpletech.MartyrMemory.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表message的Service接实现
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService{

	@Autowired
	MessageDao dao;
	
	@Override
	public int insert(Message model) throws Exception{
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Message model) throws Exception {
		Message old = findById(getModelID(model));
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
	public Message findById(Object id) throws Exception{
		return dao.findById(id);
	}

	@Override
	public List<Message> findAll() throws Exception{
		return dao.findAll();
	}

	@Override
	public int delete(String id) throws Exception{
		return dao.delete(id);
	}

	@Override
	public List<Message> findByPage(int limit, int start) throws Exception {
		return dao.findByPage(limit, start);
	}




	@Override
	public Message findById(String id) throws Exception {
		return dao.findById(id);
	}
	
	@Override
	public Page<Message> listByPage(int pageSize, int pageNo) throws Exception{
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Message> list = dao.findByPage(limit, start);
		
		return new Page<Message>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public Page<Message> listWhereByPage(String where, int pageSize, int pageNo) throws Exception {
		int limit = pageSize;
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		List<Message> whereList = dao.findWhereByPage(where, limit, start);
		return new Page<Message>(pageNo,pageSize,totalPage,totalRecord,whereList){};
	}


	@Override
	public int countAll() throws Exception {
		return dao.countAll();
	}

	@Override
	public int countByPropertyName(String propertyName, String value) throws Exception {
		return dao.countByPropertyName(propertyName, value);
	}



}
