package com.simpletech.MartyrMemory.dao.base;

import java.util.List;

/**
 * 多功能Dao层接口
 * @param <T>
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
public interface MultiDao<T> extends BaseDao<T>{
	/**
	 * 选择性删除
	 * @param where
	 * @return
	 * @throws Exception
	 */
	public int deleteWhere(String where) throws Exception;
	/**
	 * 根据属性值删除
	 * @param propertyName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public int deleteByPropertyName(String propertyName,Object value) throws Exception;
	/**
	 * 选择性统计
	 * @param where
	 * @return
	 * @throws Exception
	 */
	public int countWhere(String where) throws Exception;
	/**
	 * 根据属性统计
	 * @param propertyName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public int countByPropertyName(String propertyName,Object value) throws Exception;
	/**
	 * 选择性查询
	 * @param where
	 * @return
	 * @throws Exception
	 */
	public List<T> findWhere( String where) throws Exception;
	/**
	 * 选择性分页查询
	 * @param where
	 * @param limit
	 * @param start
	 * @return
	 * @throws Exception
	 */
	public List<T> findWhereByPage(String where, int limit,int start) throws Exception;
	/**
	 * 根据属性查询
	 * @param propertyName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<T> findByPropertyName(String propertyName,Object value) throws Exception;


	/***
	 * 管理员登录
	 * @param name 管理员名称
	 * @param password 密码
	 * @return 查询相匹配的帐号与密码的管理员集合
	 */
	List<T> findByNameAndPassword(String name, String password)throws Exception;
}
