package com.simpletech.MartyrMemory.service.base;

import com.simpletech.MartyrMemory.dao.base.BaseDao;
import com.simpletech.MartyrMemory.util.Page;

/**
 * 通用Service层接口
 * @param <T>
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
public interface BaseService<T> extends BaseDao<T>{
	int delete(String id) throws Exception;
	T findById(String id) throws Exception;
	Page<T> listByPage(int pageSize, int pageNo) throws Exception;
	Page<T> listWhereByPage(String where, int pageSize, int pageNo) throws Exception;
}
