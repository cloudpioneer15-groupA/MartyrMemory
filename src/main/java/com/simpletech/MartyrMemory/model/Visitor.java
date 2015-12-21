package com.simpletech.MartyrMemory.model;

import com.simpletech.MartyrMemory.annotations.dbmodel.Id;
import com.simpletech.MartyrMemory.annotations.dbmodel.Table;
import com.simpletech.MartyrMemory.model.base.ModelBase;

/**
 * 数据库表visitor
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Table("visitor")
public class Visitor extends ModelBase{

	/**
	 * 数据库列id
	 */
	@Id
	private String id;
	/**
	 * 数据库列visitorName
	 */
	private String visitorName;
	/**
	 * 数据库列deleteFlag
	 */
	private Boolean deleteFlag;
	/**
	 * 数据库列updateTime
	 */
	private java.util.Date updateTime;
	/**
	 * 数据库列createTime
	 */
	private java.util.Date createTime;
	/**
	 * 数据库列description
	 */
	private String description;

	public Visitor() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getVisitorName(){
		return this.visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	
	public Boolean getDeleteFlag(){
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}