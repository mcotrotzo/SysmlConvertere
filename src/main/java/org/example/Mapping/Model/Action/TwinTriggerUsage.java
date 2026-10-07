package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;

public class TwinTriggerUsage extends Usage<TwinTriggerCore, Feature, TwinTriggerDefinition> implements TwinTriggerCoreApi {
	public TwinTriggerUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinTriggerCore(sysmlElement, mapper), mapper, TwinTriggerDefinition.class);
	}
}
