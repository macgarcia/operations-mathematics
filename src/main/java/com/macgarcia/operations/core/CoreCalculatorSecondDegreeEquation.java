package com.macgarcia.operations.core;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.macgarcia.operations.core.auxiliary.OperationsExtra;
import com.macgarcia.operations.dto.in.SecondDegreeEquationDtoIn;

@Component
public class CoreCalculatorSecondDegreeEquation extends OperationsExtra {

	public ResponseEntity<?> calculator(final SecondDegreeEquationDtoIn dto) {
		
		double delta = this.delta(dto);
		
		if (delta >= 0) {
			result.put(index++, "===================");
			this.bhaskara(dto, delta);
		}
		return ResponseEntity.ok(result);
	}

	private void bhaskara(final SecondDegreeEquationDtoIn dto, final double delta) {
		
		final double raizDelta = (double) Math.sqrt(delta);
		final double twoTimesA = (double) 2 * dto.getA();
		final double raizPositive = (double) ( (dto.getB() * -1) + raizDelta ) / twoTimesA;
		final double raizNegative = (double) ( (dto.getB() * -1) - raizDelta ) / twoTimesA;
		
		result.put(index++, "X = (-b ± √∆) / 2 * a");
		result.put(index++, "X = (" + sinal(dto.getB() * -1) + dto.getB() * -1
				+ " ± √" + delta + " ) / 2 * " + dto.getA());
		result.put(index++, "X = (" + sinal(dto.getB() * -1) + dto.getB() * -1 
				+ " ± " + raizDelta + " ) / " + twoTimesA);
		
		result.put(index++, "===================");
		
		// X line one
		final double xOne = (double) (dto.getB() * -1) + raizDelta;
		
		result.put(index++, "X' = (" + sinal(dto.getB() * -1) + dto.getB() * -1 + " + " + raizDelta + " ) / " 
				+ twoTimesA);
		result.put(index++, "X' = " + xOne + " / " + twoTimesA);
		result.put(index++, "X' = " + raizPositive);
		
		result.put(index++, "===================");
		
		// X line two
		final double xTwo = (double) (dto.getB() * -1) - raizDelta;
		result.put(index++, "X'' = (" + sinal(dto.getB() * -1) + dto.getB() * -1 + " - " + raizDelta + " ) / " 
				+ twoTimesA);
		result.put(index++, "X'' = " + xTwo + " / " + twoTimesA);
		result.put(index++, "X'' = " + raizNegative);
		
	}

	private double delta(final SecondDegreeEquationDtoIn dto) {
		
		startStruct();
		
		final double bSquare = (double) (dto.getB() * dto.getB());
		final double minusFour = (double) - (4 * dto.getA() * dto.getC());
		final double delta = (double) bSquare - (4 * dto.getA() * dto.getC());
		
		result.put(index++, "∆ = b² - 4 * a * c");
		result.put(index++, "∆ = (" + dto.getB() + ")² - 4 * " + dto.getA() + " * " + dto.getC());
		result.put(index++, "∆ = " + bSquare + sinal(minusFour) + minusFour);
		result.put(index++, "∆ = " + delta);
		
		return delta;
	}

}
