package org.example.Mapping.Model.Action;


import Model.Annotation.MappedLibrary;
import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Behavior;


@MappedLibrary(libraryName = "TwinActionLibrary::TwinTriggeredAction", core = TwinTriggerActionCore.class)
public class TwinTriggerActionDefinition<C extends ActionBlockCore & TriggerCore> extends AbstractTwinActionDefinition<C> implements TwinTriggerActionCoreApi<C> {
	public TwinTriggerActionDefinition(Behavior sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}