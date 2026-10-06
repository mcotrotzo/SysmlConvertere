package org.example.Mapping.Model.Port;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Classifier;

public class TwinPortDefinition<C extends TwinPortCore> extends Definition<C, Classifier> implements TwinPortCoreApi<C> {
	public TwinPortDefinition(Classifier sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
