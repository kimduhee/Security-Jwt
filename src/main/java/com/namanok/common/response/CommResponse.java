package com.namanok.common.response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * https://codetest.3o3.co.kr/v1/scrap
 * 와 비슷한 패턴으로 response 셋팅함
 * status, data, errors (timestamp값 추가)
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommResponse {
	
	private String status;
	private HttpStatus httpStatus;
	private Object responseObj;
	private String errorCode;
	private String errorMessage;

	public ResponseEntity<Object> generateResponse() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status", status);
        map.put("data", responseObj);
        if(errorCode != null && !"".equals(errorCode)) {
        	Map<String, Object> errMap = new HashMap<String, Object>();
        	errMap.put("code", errorCode);
        	errMap.put("message", errorMessage);
        	map.put("errors", errMap);
        } else {
        	map.put("errors", "");
        }

        return new ResponseEntity<Object>(map, new HttpHeaders(), httpStatus);
    }
}