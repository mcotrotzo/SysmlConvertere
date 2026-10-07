package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.ActionUsage;

import java.util.function.Supplier;

public class TwinTriggerActionUsage<C extends ActionBlockCore & TriggerCore, D extends TwinTriggerActionDefinition<?>> extends AbstractTwinActionUsage<C, D> implements TwinTriggerActionCoreApi<C> {
	public TwinTriggerActionUsage(ActionUsage sysmlElement, Supplier<C> core, Mapper newMappe, Class<D> definitionClass) {
		super(sysmlElement, core, newMappe, definitionClass);
	}
}
