package com.zblog.core.dal.entity;

import java.io.Serializable;

public class Result<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5051374319759902963L;
	public static final int FAIL = -1;
	public static final int SUCCESS = 0;
	private boolean success;
	private String msg;
	private T data;
	private int code;
	public static Result<Object> success(){
		return new Result<>();
	}
	public static Result<Object> success(boolean success){
		return new Result<>(success);
	}
	public static Result<Object> success(String msg){
		return new Result<>(msg);
	}
	public static <T>Result<T> success(T data){
		return new Result<>(data);
	}
	public static Result<Object> fail(String msg){
		return new Result<>(msg,FAIL);
	}
	public static Result<Object> fail(String msg, int code){
		return new Result<>(msg,code);
	}
	public Result(boolean success) {
		this(success,null,null,success?SUCCESS:FAIL);
	}
	public Result(String msg) {
		this(true,msg,null,SUCCESS);
	}
	public Result() {
		this(true,null,null,SUCCESS);
	}
	public Result(T data) {
		this(true,null,data,0);
	}
	public Result(String msg, int code) {
		this(false,msg,null,code);
	}
	public Result(boolean success, String msg, T data, int code) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
