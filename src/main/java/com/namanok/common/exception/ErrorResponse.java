package com.namanok.common.exception;

import java.time.LocalDateTime;

/**
 * 에러 발생시 응답 정보 저장 클래스
 * 별도의 정보를 내려줘야 할 경우 확장 가능
 * 
 * @author namanok
 *
 */
public class ErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();	//에러 시간
    private String errorMessage; //메세지
    private String errorCode; //코드

    public ErrorResponse() {
    }

    static public ErrorResponse create() {
        return new ErrorResponse();
    }

    public ErrorResponse errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ErrorResponse errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
   
}