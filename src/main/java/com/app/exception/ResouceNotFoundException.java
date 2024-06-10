package com.app.exception;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Setter
@Getter
@Slf4j
public class ResouceNotFoundException extends RuntimeException{
	private String resourceName;
	private String fieldName;
	private long fieldvalue;
	public ResouceNotFoundException(String resourceName, String fieldName, long fieldvalue) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
		log.error(fieldName+"Not found", fieldvalue);
	}
}
