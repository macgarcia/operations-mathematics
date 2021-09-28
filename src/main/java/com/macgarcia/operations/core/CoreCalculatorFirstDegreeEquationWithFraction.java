package com.macgarcia.operations.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.macgarcia.operations.core.auxiliary.OperationsExtra;
import com.macgarcia.operations.dto.in.FirstDegreeEquationWithFractionDtoIn;
import com.macgarcia.operations.exception.ErrorProcess;

@Component
public class CoreCalculatorFirstDegreeEquationWithFraction extends OperationsExtra {

	public ResponseEntity<?> calculator(final FirstDegreeEquationWithFractionDtoIn dto) {

		if (dto.getElementDivisor() == null || dto.getElementDivisor() == 0) {
			ErrorProcess e = new ErrorProcess(406, "We will a division for zero.",
					"calculator-firstDegreeEquationWithFraction");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
		}

		if (dto.getElementA() == null || dto.getElementA() == 0) {
			ErrorProcess e = new ErrorProcess(406, "The first element is equal the zero.",
					"calculator-firstDegreeEquationWithFraction");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
		}

		final boolean elementAGreatedThenOne = dto.getElementA() > 1;

		if (dto.getElementB() == null || dto.getElementB() == 0) {
			
			if (elementAGreatedThenOne) {
				this.calculatorWhenElementAIsGreatedThenOneAndWithoutElementB(dto);
			} else {
				this.calulatorWithoutElementB(dto);
			}
			
		} else {
			
			if (elementAGreatedThenOne && !dto.isDivisorLetter()) {
				this.calculatorWhenExistsElementBAndElementAIsGreatedThenOneAndDivisorIsANumber(dto);
			} else if (elementAGreatedThenOne && dto.isDivisorLetter()) {
				this.calculatorWhenElementAIsGreatedThenOneAndDivisorIsALetter(dto);
			}
			
		}
		return ResponseEntity.ok(result);
	}

	/***
	 * 
	 * @param dto </br>
	 *            exemplo: ax +- b / x = c </br>
	 *            When a > 1 and element divisor is a letter.
	 */
	private void calculatorWhenElementAIsGreatedThenOneAndDivisorIsALetter(FirstDegreeEquationWithFractionDtoIn dto) {
		
		startStruct();
		
		final boolean divisorGreatedThenOne = dto.getElementDivisor() > 1;
		
		if (divisorGreatedThenOne) {
			
			final double elementCMultDivisor = (double) dto.getElementC() * dto.getElementDivisor();
			final double elementAAndMultDivisor = (double) elementCMultDivisor + (dto.getElementA() * -1);
			final double finalResult = (double) dto.getElementB() / elementAAndMultDivisor;
			
			result.put(index++,
					dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " / "
							+ dto.getElementDivisor() + "X = " + dto.getElementC());
			result.put(index++,
					dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " = "
					+ dto.getElementC() + " * (" + dto.getElementDivisor() + "X)");
			result.put(index++,
					dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " = "
					+ elementCMultDivisor + "X");
			result.put(index++,
					dto.getElementB() + " = " + elementCMultDivisor + "X"
					+ sinal(dto.getElementA() * -1) + dto.getElementA() * -1 + "X");
			result.put(index++, 
					dto.getElementB() + " = " + elementAAndMultDivisor + "X");
			result.put(index++, 
					dto.getElementB() + " / " + elementAAndMultDivisor + " = X");
			result.put(index++, finalResult + " = X");
			
		} else {
			
			final double mediumResult = (double) dto.getElementC() + (dto.getElementA() * -1);
			final double finalResult = (double) dto.getElementB() / ((dto.getElementA() * -1) + dto.getElementC());
			result.put(index++,
					dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " / X = " + dto.getElementC());
			result.put(index++,
					dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " = " + dto.getElementC() + "X");
			result.put(index++,
					dto.getElementB() + " = " + dto.getElementC() + "X" + sinal(dto.getElementA() * -1) + dto.getElementA() * -1 + "X");
			result.put(index++,
					dto.getElementB() + " = " + mediumResult + "X");
			result.put(index++,
					dto.getElementB() + " / " + mediumResult + " = X");
			result.put(index++,
					finalResult + " = X");
		}
	}

	/***
	 * 
	 * @param dto </br>
	 *            exemplo: ax +- b / d = c </br>
	 *            When a > 1 and c can't be a letter
	 */
	@Deprecated
	private void calculatorWhenExistsElementBAndElementAIsGreatedThenOneAndDivisorIsANumber(
			final FirstDegreeEquationWithFractionDtoIn dto) {
		
		startStruct();
		
		final double newElementC = (double) dto.getElementC() * dto.getElementDivisor();
		final double finalElementC = (double) newElementC + (dto.getElementB() * -1);
		
		result.put(index++,
				dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " / " + dto.getElementDivisor()
				+ " = " + dto.getElementC());
		result.put(index++,
				dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " = " + dto.getElementC() + " * " + dto.getElementDivisor());
		result.put(index++,
				dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " = " + newElementC);
		result.put(index++,
				dto.getElementA() + "X = " + newElementC + sinal(dto.getElementB() * -1) + dto.getElementB() * -1);
		result.put(index++,
				dto.getElementA() + "X = " + finalElementC);
		result.put(index++,
				"X = " + finalElementC + " / " + dto.getElementA());
		result.put(index++,
				"X = " + finalElementC / dto.getElementA());

	}

	/***
	 * 
	 * @param dto </br>
	 *            exemplo: ax / d = c </br>
	 *            When a > 1 and not exists elementB.
	 */
	private void calculatorWhenElementAIsGreatedThenOneAndWithoutElementB(
			final FirstDegreeEquationWithFractionDtoIn dto) {
		startStruct();
		final double resultMedium = (double) dto.getElementC() * dto.getElementDivisor();
		final double resultEquation = (double) dto.getElementC() * dto.getElementDivisor() / dto.getElementA();
		result.put(index++, dto.getElementA() + "X / " + dto.getElementDivisor() + " = " + dto.getElementC());
		result.put(index++, dto.getElementA() + "X = " + dto.getElementC() + " * " + dto.getElementDivisor());
		result.put(index++, dto.getElementA() + "X = " + resultMedium);
		result.put(index++, "X = " + resultMedium + " / " + dto.getElementA());
		result.put(index++, "X = " + resultEquation);

	}

	/***
	 * 
	 * @param dto </br>
	 *            exemplo: ax / d = c </br>
	 *            When a = 1 and not exists elementB.
	 */
	private void calulatorWithoutElementB(final FirstDegreeEquationWithFractionDtoIn dto) {
		startStruct();
		final double resultMult = (double) dto.getElementC() * dto.getElementDivisor();
		result.put(index++, "X / " + dto.getElementDivisor() + " = " + dto.getElementC());
		result.put(index++, "X = " + dto.getElementC() + " * " + dto.getElementDivisor());
		result.put(index++, "X = " + resultMult);
	}

}
