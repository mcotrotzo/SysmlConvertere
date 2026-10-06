package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.ActionUsage;

import java.util.function.Supplier;

/** Common base of all action usages. U: SysML element, D: mapped definition. */
public abstract class TwinActionUsage<C extends Core<? super U>, U extends ActionUsage, D extends TwinActionDefinition> extends Usage<C, U, D> {
	protected TwinActionUsage(U sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
