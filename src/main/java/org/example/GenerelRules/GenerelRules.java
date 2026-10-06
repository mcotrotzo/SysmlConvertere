package org.example.GenerelRules;


import org.example.Util.NewUtil;
import org.example.Util.Utils;


public abstract class GenerelRules {

	protected final NewUtil utilsManager;

	public GenerelRules(NewUtil utils) {
		this.utilsManager = utils;
	}

	public abstract boolean isValid() throws IllegalArgumentException;
}
