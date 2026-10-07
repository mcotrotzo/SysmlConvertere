package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.ActionUsage;

import java.util.function.Supplier;

public class TwinActionBlockUsage<C extends ActionBlockCore, D extends TwinActionBlockDefinition> extends AbstractTwinActionUsage<C, D> implements ActionBlockCoreApi<C> {
	public TwinActionBlockUsage(ActionUsage sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
