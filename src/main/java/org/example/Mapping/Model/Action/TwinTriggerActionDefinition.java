package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Behavior;

import java.util.function.Supplier;

public class TwinTriggerActionDefinition<C extends ActionBlockCore & TriggerCore> extends AbstractTwinActionDefinition<C> implements TwinTriggerActionCoreApi<C> {
	public TwinTriggerActionDefinition(Behavior sysmlElement, Supplier<C> core, Mapper newMappe) {
		super(sysmlElement, core, newMappe);
	}
}