package org.example.GenerelRules;

import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.Usage;
import org.omg.sysml.util.TypeUtil;

import java.util.HashSet;
import java.util.Set;


public abstract class GenerelRules {

	protected final Utils utilsManager;

	public GenerelRules(Utils utils) {
		this.utilsManager = utils;
	}

	public abstract boolean isValid() throws MappingException;
}
