package com.hm2.common.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableAjax<T> {
	private Integer draw = 0;
	private Integer recordsTotal = 0;
	private Integer recordsFiltered = 0;
	private List<T> data;

	}
