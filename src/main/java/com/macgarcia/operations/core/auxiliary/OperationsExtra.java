package com.macgarcia.operations.core.auxiliary;

import java.util.HashMap;
import java.util.Map;

public class OperationsExtra {
	
	protected int index;
	protected Map<Integer, String> result;
	
	protected String sinal(Integer n) {
		if (n > 0) {
			return " + ";
		}
		return "";
	}

	protected void startStruct() {
		index = 0;
		result = new HashMap<Integer, String>();
	}

}
