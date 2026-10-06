package org.example.Mapping.Model.EnumAttribute;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.example.Mapping.Model.Type.EmptyCore;
import org.omg.sysml.lang.sysml.Classifier;

public class EnumDefinition extends Definition<EmptyCore, Classifier> {
	public EnumDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, mapper), mapper);
	}
}
