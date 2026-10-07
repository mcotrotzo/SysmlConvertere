package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.ActionUsage;

import java.util.function.Supplier;

public class AbstractTwinActionUsage<C extends ActionBlockCore, D extends AbstractTwinActionDefinition> extends TwinActionUsage<C, ActionUsage, D> implements ActionBlockCoreApi<C> {
	public AbstractTwinActionUsage(ActionUsage sysmlElement, Supplier<C> core, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, core, mapper, definitionClass);
	}
}
