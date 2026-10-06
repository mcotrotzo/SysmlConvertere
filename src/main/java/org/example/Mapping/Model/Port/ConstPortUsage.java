package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class ConstPortUsage extends TwinPortUsage<ConstPortCore, ConstPortDefinition> implements ConstPortCoreApi {
	public ConstPortUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ConstPortCore(sysmlElement, mapper), mapper, ConstPortDefinition.class);
	}
}
