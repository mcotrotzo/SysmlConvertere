package org.example.Mapping.Model.StateMachine;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.omg.sysml.lang.sysml.ActionUsage;

/** Library types State, ControlUnitState, DescriptiveState. */
public class TwinStateUsage extends TwinActionBlockUsage<TwinStateCore, TwinStateDefinition> implements TwinStateCoreApi<TwinStateCore> {
	public TwinStateUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinStateCore(sysmlElement, mapper), mapper, TwinStateDefinition.class);
	}
}
