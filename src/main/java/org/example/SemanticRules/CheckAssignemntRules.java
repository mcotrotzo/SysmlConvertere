package org.example.SemanticRules;

import org.example.TwinDataBase;

public class CheckAssignemntRules implements SemanticRule {


	@Override
	public boolean isValid(TwinDataBase database) throws SemanticException {
		return true;
	}

}