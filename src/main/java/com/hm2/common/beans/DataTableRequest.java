package com.hm2.common.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableRequest implements Serializable {

	private static final long serialVersionUID = -2101666256658867880L;
	private Integer start;
	private Integer length;
	List<DataTableSort> sort = new ArrayList<DataTableSort>();
	private Integer draw;

}
