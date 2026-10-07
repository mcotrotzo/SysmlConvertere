package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Classifier;

public class TwinTriggerDefinition extends Definition<TwinTriggerCore, Classifier> implements TwinTriggerCoreApi {
	public TwinTriggerDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinTriggerCore(sysmlElement, mapper), mapper);
	}
}
