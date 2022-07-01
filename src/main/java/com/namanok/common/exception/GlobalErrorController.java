package com.namanok.common.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namanok.common.response.CommResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템, 업무 예외 발생시 처리하는 클래스
 */
@Slf4j
@ControllerAdvice
@RestController
public class GlobalErrorController {

	/**
	 * BaseException 처리
	 * 
	 * desc :
	 * 		RuntimeException을 상속받아서 enum에 에러를 정의하여 처리함
	 * 
	 * @param e
	 * @param handlerMethod
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = BizException.class)
	public Object handleBizException(BizException e) {
		ErrorEnum errorCode= e.getErrorEnum();
		String code = e.getCode();
		String message = e.getMessage();
		String type = e.getType();
		if("enum".equals(type)) {
			code = errorCode.getCode();
			message = errorCode.getMessage();
		}
			
		if(log.isErrorEnabled()) {
			log.error("## BizException");
			log.error("## errorCode:{}", code);
			log.error("## errorMessage:{}", message);
		}

		CommResponse response = CommResponse.builder()
				.errorCode(code)
				.errorMessage(message)
				.httpStatus(HttpStatus.OK)
				.responseObj("")
				.status("fail")
				.build();
		return response.generateResponse();
	}

	/**
	 * RuntimeException 처리
	 * 
	 * @param e
	 * @param handlerMethod
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = RuntimeException.class)
	public Object handleRuntimeException(RuntimeException e) {
		ErrorEnum errorCode= ErrorEnum.SYS9999;	//시스템 오류가 발생했습니다. 잠시후 다시 시도해 주세요.
		if(log.isErrorEnabled()) {
			log.error("## RuntimeException :{}", e);
		}

		CommResponse response = CommResponse.builder()
				.errorCode(errorCode.getCode())
				.errorMessage(errorCode.getMessage())
				.httpStatus(HttpStatus.OK)
				.responseObj("")
				.status("fail")
				.build();
		return response.generateResponse();
	}
	
	/**
	 * valid 관련 exception 처리
	 * 
	 * desc :
	 * 		모든 필드의 유효성 검증 결과를 리턴 할 수도 있지만 일단 첫번째(순서없음) 검증오류만 리턴함
	 * 		validation 관련 에러는 모두 코드를 'ERRVALID'로 처리함
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<String> errors = e.getBindingResult().getAllErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		if(log.isErrorEnabled()) {
			log.error("## errorMessage:{}", errors.get(0));
		}

		CommResponse response = CommResponse.builder()
				.errorCode("ERRVALID")
				.errorMessage(errors.get(0))
				.httpStatus(HttpStatus.OK)
				.responseObj("")
				.status("fail")
				.build();
		return response.generateResponse();
	}
	
	@PostMapping(value="/exception")
	public ResponseEntity<Object> exception(HttpServletRequest request) {
		
		ErrorEnum errorEnum = (ErrorEnum)request.getAttribute("exception");
		
		CommResponse response = CommResponse.builder()
				.errorCode(errorEnum.getCode())
				.errorMessage(errorEnum.getMessage())
				.httpStatus(HttpStatus.OK)
				.responseObj("")
				.status("fail")
				.build();
		return response.generateResponse();
	}
}
