package com.hm2.common.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelValueBean implements Serializable {

	private static final long serialVersionUID = -4903724438420463149L;
	private String label;
	private String value;

	public LabelValueBean(String label, String value) {
		// TODO Auto-generated constructor stub
		this.label = label;
		this.label = value;
		
	}

	@Override
	public String toString() {
		return "LabelValueBean [label=" + label + ", value=" + value + "]";
	}

}
