package org.example.Mapping.Model.StateMachine;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.omg.sysml.lang.sysml.ActionUsage;

/** Library types State, ControlUnitState, DescriptiveState. */
@MappedLibrary(libraryName = "TwinStateMachineLibrary::State", core = TwinStateCore.class)
public class TwinStateUsage extends TwinActionBlockUsage<TwinStateCore, TwinStateDefinition> implements TwinStateCoreApi<TwinStateCore> {
	public TwinStateUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
