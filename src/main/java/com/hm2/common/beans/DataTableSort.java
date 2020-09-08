package com.hm2.common.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableSort implements Serializable {

	
	private static final long serialVersionUID = -6514405829011582536L;
	private String column;
	private String order;

}
