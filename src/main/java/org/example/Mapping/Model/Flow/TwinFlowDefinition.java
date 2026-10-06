package org.example.Mapping.Model.Flow;


import org.example.Mapping.Model.Action.TwinActionDefinition;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.EmptyCore;
import org.omg.sysml.lang.sysml.Behavior;

public class TwinFlowDefinition extends TwinActionDefinition<EmptyCore> {
	public TwinFlowDefinition(Behavior sysmlElement, Mapper newMappe) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, newMappe), newMappe);
	}
}
