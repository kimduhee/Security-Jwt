package com.namanok.common.exception;

/**
 * RuntimeException를 상속받아 사용자 exception 처리함
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -3024960150975725476L;
	
	private ErrorEnum error;
	private String code;
	private String message;
	private String type;	// enum : 에러 enum에서 가져와서 처리, code : 전달받은 에러 code와 message를 그대로 사용
	
	public BizException(ErrorEnum error) {
		super(error.getMessage());
		this.error = error;
		this.type = "enum";
	}

	public BizException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
		this.type = "code";
	}
	
	public ErrorEnum getErrorEnum() {
		return error;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public String getType() {
		return type;
	}
}
