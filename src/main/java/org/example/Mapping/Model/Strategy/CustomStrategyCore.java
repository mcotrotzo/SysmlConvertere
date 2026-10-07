package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Action.TwinTriggerActionCore;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Type;

public class CustomStrategyCore extends TwinTriggerActionCore {
	public CustomStrategyCore(Type sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}
