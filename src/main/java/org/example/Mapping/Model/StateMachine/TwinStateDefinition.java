package org.example.Mapping.Model.StateMachine;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Action.TwinActionBlockDefinition;
import org.omg.sysml.lang.sysml.Behavior;

/** Library types State, ControlUnitState, DescriptiveState. */
@MappedLibrary(libraryName = "TwinStateMachineLibrary::State", core = TwinStateCore.class)
public class TwinStateDefinition extends TwinActionBlockDefinition<TwinStateCore> implements TwinStateCoreApi<TwinStateCore> {
	public TwinStateDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
