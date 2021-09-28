package com.macgarcia.operations.dto.in;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FirstDegreeEquationDtoIn implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer elementA;
	private Integer elementB;
	private Integer elementC;
	
	public FirstDegreeEquationDtoIn(Integer elementA, Integer elementB, Integer elementC) {
		this.elementA = elementA;
		this.elementB = elementB;
		this.elementC = elementC;
	}
	
	@Override
	public String toString() {
		return "FirstDegreeEquationDtoIn [elementA=" + elementA + ", elementB=" + elementB + ", elementC=" + elementC
				+ "]";
	}



}
