package org.example.GenerelRules;

import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.Utils;


public abstract class GenerelRules {

	protected final Utils utilsManager;

	public GenerelRules(Utils utils) {
		this.utilsManager = utils;
	}

	public abstract boolean isValid() throws MappingException;
}
