package com.macgarcia.operations.dto.in;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FirstDegreeEquationWithFractionDtoIn implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer elementA;	
	private Integer elementB;
	private Integer elementC;
	private Integer elementDivisor;
	private boolean divisorLetter;
	
}
