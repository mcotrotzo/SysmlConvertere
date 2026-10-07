package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Behavior;

import java.util.function.Supplier;

public class AbstractTwinActionDefinition<C extends ActionBlockCore> extends TwinActionDefinition<C> implements ActionBlockCoreApi<C> {
	public AbstractTwinActionDefinition(Behavior sysmlElement, Supplier<C> core, Mapper mapper) {
		super(sysmlElement, core, mapper);
	}
}
