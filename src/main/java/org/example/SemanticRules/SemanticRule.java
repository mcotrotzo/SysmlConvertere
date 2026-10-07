package org.example.SemanticRules;


import org.example.TwinDataBase;
import org.example.Util.NewUtil;

public abstract class SemanticRule {


	protected NewUtil newUtil;

	public SemanticRule(NewUtil newUtil){
		this.newUtil = newUtil;
	}
	public abstract boolean isValid(TwinDataBase database) throws SemanticException;


}
