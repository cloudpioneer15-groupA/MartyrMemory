package com.simpletech.MartyrMemory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.MartyrMemory.dao.base.BaseDaoImpl;
import com.simpletech.MartyrMemory.dao.MessageDao;
import com.simpletech.MartyrMemory.model.Message;

/**
 * 数据库表message的Dao实现
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao{

	@Override
	public int insert(Message t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Message t) throws Exception {
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
	public Message findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Message> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Message> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}


	@Override
	public int countByPropertyName(String propertyName, String value) throws Exception {
		return super.countByPropertyName(propertyName, value);
	}

	@Override
	public List<Message> findWhereByPage(String order, String where, int limit, int start) throws Exception {
		return super.findWhereByPage(where, limit, start);
	}

}

