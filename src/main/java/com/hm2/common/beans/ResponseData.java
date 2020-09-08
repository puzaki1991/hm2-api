package com.hm2.common.beans;

import lombok.Data;

@Data
public class ResponseData<T> {
	private String status;
	private String message;
	private T data;
	private long rows = 0l;
}