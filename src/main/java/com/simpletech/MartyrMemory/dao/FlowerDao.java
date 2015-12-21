package com.simpletech.MartyrMemory.dao;

import java.util.List;

import com.simpletech.MartyrMemory.dao.base.BaseDao;
import com.simpletech.MartyrMemory.model.Flower;

/**
 * 数据库表flower的Dao接口
 * @author zimu
 * @date 2015-11-16 14:34:13 中国标准时间
 */
public interface FlowerDao extends BaseDao<Flower>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变行数
	 */
	int insert(Flower model) throws Exception;
	/**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 改变行数
	 */
	int delete(Object id) throws Exception;
	/**
	 * 更新一条数据
	 * @param model 需要更新数据
	 * @return 改变行数
	 */
	int update(Flower model) throws Exception;
	/**
	 * 统计全部出数据
	 * @return 全部数据量
	 */
	int countAll() throws Exception;
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return 数据对象 or null
	 */
	Flower findById(Object id) throws Exception;
	/**
	 * 获取全部数据
	 * @return 全部所有数据
	 */
	List<Flower> findAll() throws Exception;
	/**
	 * 分页查询数据
	 * @param limit 分页最大值
	 * @param start 开始编号
	 * @return 分页列表数据
	 */
	List<Flower> findByPage(int limit,int start) throws Exception;
	

}
