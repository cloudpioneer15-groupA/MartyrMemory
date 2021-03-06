package com.simpletech.MartyrMemory.model.entity;

/**
 * Restful返回实体类
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
public class RestfulEntity{

	private boolean status;

	private Object result;

	public RestfulEntity(Object result, boolean status) {
		this.result = result;
		this.status = status;
	}

	/**
	 * 返回成功信息
	 * 
	 * @param mes
	 * @return String
	 */
	public static RestfulEntity getSuccess(Object mes) {
		return new RestfulEntity(mes, true);
	}

	/**
	 * 返回失败信息
	 * 
	 * @param mes
	 * @return String
	 */
	public static RestfulEntity getFailure(Object mes) {
		return new RestfulEntity(mes, false);
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
