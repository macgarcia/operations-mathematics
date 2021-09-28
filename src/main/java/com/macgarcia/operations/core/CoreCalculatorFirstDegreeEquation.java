package com.macgarcia.operations.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.macgarcia.operations.core.auxiliary.OperationsExtra;
import com.macgarcia.operations.dto.in.FirstDegreeEquationDtoIn;
import com.macgarcia.operations.exception.ErrorProcess;

@Component
public class CoreCalculatorFirstDegreeEquation extends OperationsExtra {

	public ResponseEntity<?> calculator(final FirstDegreeEquationDtoIn dto) {

		if (dto.getElementA() == null || dto.getElementA() == 0) {
			final ErrorProcess e = new ErrorProcess(406, "Fisrt element is equal null.",
					"calculator-firstDegreeEquation");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
		}

		if (dto.getElementC() == null) {
			final ErrorProcess e = new ErrorProcess(406, "Third element is null.", "calculator-firstDegreeEquation");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
		}

		final boolean secondElement = dto.getElementB() == null || dto.getElementB() == 0;
		final boolean thirdElement = dto.getElementC() != null && dto.getElementC() == 0;

		if (secondElement && thirdElement) {
			final ErrorProcess e = new ErrorProcess(406, "Will have a divisio for zero.",
					"calculator-firstDegreeEquation");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
		} else if (secondElement && !thirdElement) {
			this.calculatorFirstDegreeEquationWithoutElementB(dto);
		} else {
			if (dto.getElementA() > 1) {
				this.calculatorFirstDegreeEquationWhenElementAIsGreatedThenOne(dto);
			} else {
				this.calculatorFirstDegreeEquation(dto);
			}
		}
		return ResponseEntity.ok(result);
	}

	private void calculatorFirstDegreeEquationWhenElementAIsGreatedThenOne(final FirstDegreeEquationDtoIn dto) {

		startStruct();
		final int resultMedium = (dto.getElementC() + (dto.getElementB() * -1));
		final double elementResult = (double) (dto.getElementC() + (dto.getElementB() * -1)) / dto.getElementA();

		result.put(index++,
				dto.getElementA() + "X" + sinal(dto.getElementB()) + dto.getElementB() + " = " + dto.getElementC());
		result.put(index++, dto.getElementA() + "X = " + dto.getElementC() + sinal(dto.getElementB() * -1)
				+ (dto.getElementB() * -1));
		result.put(index++, dto.getElementA() + "X = " + resultMedium);
		result.put(index++, "X = " + resultMedium + "/" + dto.getElementA());
		result.put(index++, "X = " + elementResult);
	}

	private void calculatorFirstDegreeEquation(final FirstDegreeEquationDtoIn dto) {

		startStruct();
		final int elementResult = (dto.getElementC() + (dto.getElementB() * -1));

		result.put(index++, "X" + sinal(dto.getElementB()) + dto.getElementB() + " = " + dto.getElementC());
		result.put(index++, "X = " + dto.getElementC() + sinal(dto.getElementB() * -1) + (dto.getElementB() * -1));
		result.put(index++, "X = " + elementResult);
	}

	private void calculatorFirstDegreeEquationWithoutElementB(final FirstDegreeEquationDtoIn dto) {

		startStruct();
		final double elementResult = (double) dto.getElementC() / dto.getElementA();

		result.put(index++, dto.getElementA() + "X = " + dto.getElementC());
		result.put(index++, "X = " + dto.getElementC() + "/" + dto.getElementA());
		result.put(index++, "X = " + elementResult);
	}
}
