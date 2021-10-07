package com.macgarcia.operations.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.macgarcia.operations.core.auxiliary.OperationsExtra;
import com.macgarcia.operations.dto.in.TriangleRectangle;
import com.macgarcia.operations.exception.ErrorProcess;

@Component
public class CoreCalculatorHypotenuse extends OperationsExtra {

	public ResponseEntity<?> calculator(final TriangleRectangle dto) {

		if (dto.getCatOne() == 0 && dto.getCatTwo() == 0) {
			final ErrorProcess e = new ErrorProcess(406, "All catetos has value zero", "calculator-hypotenuse");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
		}

		if (dto.getCatOne() == 0 || dto.getCatTwo() == 0) {
			this.findCateto(dto);
		} else {
			this.findHypotenuse(dto);
		}

		return ResponseEntity.ok(result);
	}

	private void findCateto(final TriangleRectangle dto) {

		startStruct();

		result.put(index++, "c² + c² = h²");

		int finalResult = 0;
		int squareHypotenuse = dto.getHyp() * dto.getHyp();

		if (dto.getCatOne() == 0) {

			int squareCatTwo = dto.getCatTwo() * dto.getCatTwo();

			finalResult = squareHypotenuse - squareCatTwo;
			result.put(index++, "c²" + sinal(dto.getCatTwo()) + dto.getCatTwo() + "² = " + dto.getHyp() + "²");
			result.put(index++, "c²" + sinal(squareCatTwo) + squareCatTwo + " = " + squareHypotenuse);
			result.put(index++, "c² = " + squareHypotenuse + sinal(squareCatTwo * -1) + (squareCatTwo * -1));
			result.put(index++, "c² = " + finalResult);
			result.put(index++, "c = √" + finalResult);
			result.put(index++, "c = " + Math.sqrt(finalResult));

		} else {

			int squareCatOne = dto.getCatOne() * dto.getCatOne();
			finalResult = squareHypotenuse - squareCatOne;
			result.put(index++, "c²" + sinal(dto.getCatOne()) + dto.getCatOne() + "² = " + dto.getHyp() + "²");
			result.put(index++, "c²" + sinal(squareCatOne) + squareCatOne + " = " + squareHypotenuse);
			result.put(index++, "c² = " + squareHypotenuse + sinal(squareCatOne * -1) + (squareCatOne * -1));
			result.put(index++, "c² = " + finalResult);
			result.put(index++, "c = √" + finalResult);
			result.put(index++, "c = " + Math.sqrt(finalResult));

		}

	}

	private void findHypotenuse(final TriangleRectangle dto) {

		startStruct();

		final int squareCatOne = dto.getCatOne() * dto.getCatOne();
		final int squareCatTwo = dto.getCatTwo() * dto.getCatTwo();
		final int resultFinal = squareCatOne + squareCatTwo;

		result.put(index++, "h² = c² + c²");
		result.put(index++, "h² = " + sinal(dto.getCatOne()) + dto.getCatOne() + "²" + sinal(dto.getCatTwo())
				+ dto.getCatTwo() + "²");
		result.put(index++, "h² = " + squareCatOne + " + " + squareCatTwo);
		result.put(index++, "h² = " + resultFinal);
		result.put(index++, "h = √" + resultFinal);
		result.put(index++, "h = " + Math.sqrt(resultFinal));

	}

}
