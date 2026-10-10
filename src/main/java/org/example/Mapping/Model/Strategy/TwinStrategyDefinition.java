package org.example.Mapping.Model.Strategy;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Action.TwinTriggerActionCore;
import org.example.Mapping.Model.Action.TwinTriggerActionDefinition;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.TwinActionBlockDefinition;
import org.omg.sysml.lang.sysml.Behavior;


@MappedLibrary(libraryName = "TwinStrategyLibrary::Strategy", core = TwinTriggerActionCore.class)
public class TwinStrategyDefinition<C extends TwinTriggerActionCore> extends TwinTriggerActionDefinition<C> {
	public TwinStrategyDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
