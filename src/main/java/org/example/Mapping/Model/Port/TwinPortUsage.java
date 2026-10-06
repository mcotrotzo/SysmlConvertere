package org.example.Mapping.Model.Port;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;

public class TwinPortUsage<C extends TwinPortCore, D extends TwinPortDefinition> extends Usage<C, Feature, D> implements TwinPortCoreApi<C> {
	public TwinPortUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
