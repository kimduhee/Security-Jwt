package com.namanok.common.exception;

import java.time.LocalDateTime;

/**
 * ���� �߻��� ���� ���� ���� Ŭ����
 * ������ ������ ������� �� ��� Ȯ�� ����
 * 
 * @author namanok
 *
 */
public class ErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();	//���� �ð�
    private String errorMessage; //�޼���
    private String errorCode; //�ڵ�

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