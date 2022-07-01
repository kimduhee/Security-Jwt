package com.namanok.common.exception;

/**
 * 에러코드/메세지 정의 클래스
 */
public enum ErrorEnum {
	//시스템 에러 정의
	SYS9999("SYS9999", "시스템 오류가 발생했습니다. 잠시후 다시 시도해 주세요."),

	//업무 에러 정의
	ERR0000("ERR0000", "일시적인 오류가 발생했습니다. 잠시후 다시 시도해주세요."),
	ERR0001("ERR0001", "회원가입이 불가능 합니다."),
	ERR0002("ERR0002", "이미 존재하는 아이디입니다."),
	ERR0003("ERR0003", "이미 존재하는 회원입니다."),
	ERR0004("ERR0004", "회원정보가 존재하지 않습니다."),
	ERR0005("ERR0005", "토큰 유효시간이 만료되었습니다. 다시 로그인 해 주세요"),
	ERR0008("ERR0008", "로그인에 실패하였습니다."),
	ERR0010("ERR0010", "아이디 또는 비밀번호를 입력해주세요"),
	ERR9999("ERR9999", "오류가 발생했습니다. 잠시후 다시 시도해 주세요.");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorEnum(final String code, final String message) {
        this.message = message;
        this.code = code;
    }
}
