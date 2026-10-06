package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class ConstPortDefinition extends TwinPortDefinition<ConstPortCore> implements ConstPortCoreApi {
	public ConstPortDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ConstPortCore(sysmlElement, mapper), mapper);
	}
}
