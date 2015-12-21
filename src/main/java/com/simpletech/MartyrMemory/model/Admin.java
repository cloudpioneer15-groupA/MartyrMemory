package com.simpletech.MartyrMemory.model;

import com.simpletech.MartyrMemory.annotations.dbmodel.Id;
import com.simpletech.MartyrMemory.annotations.dbmodel.Table;
import com.simpletech.MartyrMemory.model.base.ModelBase;

/**
 * 数据库表admin
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Table("admin")
public class Admin extends ModelBase{

	/**
	 * 数据库列id
	 */
	@Id
	private String id;
	/**
	 * 数据库列adminName
	 */
	private String adminName;
	/**
	 * 数据库列password
	 */
	private String password;
	/**
	 * 数据库列isLogin
	 */
	private Integer isLogin;
	/**
	 * 数据库列createTime
	 */
	private java.util.Date createTime;
	/**
	 * 数据库列updateTime
	 */
	private java.util.Date updateTime;
	/**
	 * 数据库列description
	 */
	private String description;
	/**
	 * 数据库列deleteFlag
	 */
	private Boolean deleteFlag;

	public Admin() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAdminName(){
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getPassword(){
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getIsLogin(){
		return this.isLogin;
	}

	public void setIsLogin(Integer isLogin) {
		this.isLogin = isLogin;
	}
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getDeleteFlag(){
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}