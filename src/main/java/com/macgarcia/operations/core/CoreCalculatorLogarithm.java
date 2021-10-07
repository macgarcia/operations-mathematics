package com.macgarcia.operations.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.macgarcia.operations.core.auxiliary.OperationsExtra;
import com.macgarcia.operations.dto.in.LogarithmDtoIn;
import com.macgarcia.operations.exception.ErrorProcess;

@Component
public class CoreCalculatorLogarithm extends OperationsExtra {

	public ResponseEntity<?> calculator(final LogarithmDtoIn dto) {
		
		final boolean logGreaterthanZero = dto.getLog() > 0;
		final boolean baseGreaterthanZeroAndDifferentByOne = dto.getBase() > 0 && dto.getBase() != 1; 
		
		if ( !(logGreaterthanZero && baseGreaterthanZeroAndDifferentByOne) ) {
			final ErrorProcess e = new ErrorProcess(406,
					"Log has to be greater then by zero. Base has to be greater then by zero and different by one.",
					"calculator-logarithm");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
		}
		
		this.calculatorLogarithm(dto);
		
		return ResponseEntity.ok(result);
	}

	private void calculatorLogarithm(LogarithmDtoIn dto) {
		
		startStruct();
		final double finalResult = this.factorLog(dto);
		
		result.put(index++, "Log " + dto.getLog() + " in base " + dto.getBase() + " = x");
		result.put(index++, dto.getBase() + "ˣ = " + dto.getBase() + " ^ " + finalResult);
		result.put(index++, "X = " + finalResult);
		
		if (finalResult == 0) {
			result.put(index++, "Error, logarithm nonexistent.");
		}
	}

	private double factorLog(final LogarithmDtoIn dto) {
		
		int i = 0;
		boolean active = true;
		int base = dto.getBase();
		double result = dto.getLog();
		
		while(active) {
			
			if (result == 1) {
				active = false;
			} else {
				result = result / base;
				i++;
			}
			
			if (String.valueOf(result).contains("E-")) {
				active = false;
				i = 0;
			}
		}
		
		return i;
	}
	
}
