package com.tbk.ymm.data.catcher.utils;

public class ResultDTO<T> {

	private T result;
	private boolean success;
	private int code;// 2011/2/22 by dawei.qi 错误码 默认0吧,具体意义可以根据不同需求自定义
	private String failMsg;

	public ResultDTO(final T result, final boolean success, final String failMsg) {
		super();
		this.result = result;
		this.success = success;
		this.failMsg = failMsg;
	}

	public T getResult() {
		return result;
	}

	public boolean getSuccess() {
		return success;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setResult(final T result) {
		this.result = result;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public void setFailMsg(final String failMsg) {
		this.failMsg = failMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public static <T> ResultDTO<T> fail(final T a) {
		return new ResultDTO<T>(null, false, "");
	}

	public static <T> ResultDTO<T> fail(final String failMsg) {
		return new ResultDTO<T>(null, false, failMsg);
	}

	public static <T> ResultDTO<T> success(final T result) {
		return new ResultDTO<T>(result, true, "");
	}
}
