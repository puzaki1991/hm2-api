package com.hm2.common.beans;

import lombok.Data;

@Data
public class PaginationRequest {
	private int page = 1;
	private int pageSize = 10;
}
