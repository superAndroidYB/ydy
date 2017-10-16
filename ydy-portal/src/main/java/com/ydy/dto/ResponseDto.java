package com.ydy.dto;

import java.util.List;

public class ResponseDto {

	private Boolean success;
	private String msg;
	private Object data;
	private List<Object> datas;

	public ResponseDto(Boolean success, String msg) {
		this(success, msg, null, null);
	}
	
	public ResponseDto(Boolean success, String msg, Object data) {
		this(success, msg, data, null);
	}
	
	public ResponseDto(Boolean success, String msg, List<Object> datas) {
		this(success, msg, null, datas);
	}

	public ResponseDto(Boolean success, String msg, Object data, List<Object> datas) {
		this.success = success;
		this.msg = msg;
		this.data = data;
		this.datas = datas;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Object> getDatas() {
		return datas;
	}

	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}
}
