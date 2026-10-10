package org.example.Mapping.Model.StateMachine;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Action.TwinTriggerActionUsage;
import org.omg.sysml.lang.sysml.ActionUsage;

/** Library types TwinStateMachine, ControlUnit, DescriptiveStateMachine. */
@MappedLibrary(libraryName = "TwinStateMachineLibrary::TwinStateMachine", core = TwinStateMachineCore.class)
public class TwinStateMachineUsage extends TwinTriggerActionUsage<TwinStateMachineCore, TwinStateMachineDefinition> implements TwinStateMachineCoreApi<TwinStateMachineCore> {
	public TwinStateMachineUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
