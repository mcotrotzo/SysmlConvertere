package org.example.Mapping.Model.Action;


import Model.Annotation.MappedLibrary;
import Mapper.Mapper;
import org.omg.sysml.lang.sysml.ActionUsage;


@MappedLibrary(libraryName = "TwinActionLibrary::TwinTriggeredAction", core = TwinTriggerActionCore.class)
public class TwinTriggerActionUsage<C extends ActionBlockCore & TriggerCore, D extends TwinTriggerActionDefinition<?>> extends AbstractTwinActionUsage<C, D> implements TwinTriggerActionCoreApi<C> {
	public TwinTriggerActionUsage(ActionUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}
