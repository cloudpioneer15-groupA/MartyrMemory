package com.simpletech.MartyrMemory.model;

import com.simpletech.MartyrMemory.annotations.dbmodel.Id;
import com.simpletech.MartyrMemory.annotations.dbmodel.Table;
import com.simpletech.MartyrMemory.model.base.ModelBase;

/**
 * 数据库表message
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@Table("message")
public class Message extends ModelBase implements  Comparable<Message>{

	//按照时间排序
	@Override
	public int compareTo(Message o) {
		if(o.getCreateTime().getTime() - this.getCreateTime().getTime() > 0){
			return 1;
		}else if(o.getCreateTime().getTime() - this.getCreateTime().getTime() == 0){
			if(o.getVisitor().getVisitorName().compareTo(this.getVisitor().getVisitorName()) > 0){
				return 1;
			}else if(o.getVisitor().getVisitorName().compareTo(this.getVisitor().getVisitorName()) == 0){
					if(o.getContent().compareTo(this.getContent()) > 0){
						return 1;
					}else {
						return 0;
					}
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}

	/**
	 * 数据库列id
	 */
	@Id
	private String id;
	/**
	 * 数据库列visitorId
	 */
	private String visitorId;
	/**
	 * 数据库列content
	 */
	private String content;
	/**
	 * 数据库列status
	 */
	private String status;
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

	/***
	 * 同时存储其对应的访问者
	 */
	private Visitor visitor;

	public static  final String UNCHECKED_MSG = "0";
	public static  final String LEGAL_MSG = "1";
	public static  final String ILLEGAL_MSG = "2";


	public Message() {
	}
	
	public String getId(){
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getVisitorId(){
		return this.visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}
	
	public String getContent(){
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getStatus(){
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	@Override
	public String toString() {
		return "Message{" +
				"createTime=" + createTime +
				", status='" + status + '\'' +
				", deleteFlag=" + deleteFlag +
				'}';
	}
}