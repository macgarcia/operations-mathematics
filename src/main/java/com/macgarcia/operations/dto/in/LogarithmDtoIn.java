package com.macgarcia.operations.dto.in;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LogarithmDtoIn implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer log;
	private Integer base;

}
