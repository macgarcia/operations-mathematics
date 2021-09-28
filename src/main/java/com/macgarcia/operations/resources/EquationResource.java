package com.macgarcia.operations.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.macgarcia.operations.core.CoreCalculatorFirstDegreeEquation;
import com.macgarcia.operations.core.CoreCalculatorFirstDegreeEquationWithFraction;
import com.macgarcia.operations.dto.in.FirstDegreeEquationDtoIn;

@RestController
@RequestMapping("/equations")
public class EquationResource {

	@Autowired
	CoreCalculatorFirstDegreeEquation coreCalculatorFirstDegreeEquation;

	@Autowired
	CoreCalculatorFirstDegreeEquationWithFraction coreCalculatorFirstDegreeEquationWithFraction;

	@GetMapping(path = "/firstDegreeEquation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> firstDegreeEquation(@RequestParam final Integer a, @RequestParam final Integer b,
			@RequestParam final Integer c) {
		final FirstDegreeEquationDtoIn dto = new FirstDegreeEquationDtoIn(a,b,c);
		return this.coreCalculatorFirstDegreeEquation.calculator(dto);
	}

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/firstDegreeEquation/withFraction")
//	public Response firstDegreeEquationWithFraction(@BeanParam final FirstDegreeEquationWithFractionDtoIn dto) {
//		return this.coreCalculatorFirstDegreeEquationWithFraction.calculator(dto);
//	}
}
