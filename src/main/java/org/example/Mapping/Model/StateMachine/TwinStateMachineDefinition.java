package org.example.Mapping.Model.StateMachine;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Action.TwinTriggerActionDefinition;
import org.omg.sysml.lang.sysml.Behavior;

/** Library types TwinStateMachine, ControlUnit, DescriptiveStateMachine. */
@MappedLibrary(libraryName = "TwinStateMachineLibrary::TwinStateMachine", core = TwinStateMachineCore.class)
public class TwinStateMachineDefinition extends TwinTriggerActionDefinition<TwinStateMachineCore> implements TwinStateMachineCoreApi<TwinStateMachineCore> {
	public TwinStateMachineDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
