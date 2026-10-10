package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.Predefined.MetaClasses.Action.SuccessionMapUsage;
import Model.EmptyCore;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;

@MappedMetaClass(value = SuccessionAsUsage.class, core = EmptyCore.class)
public class TwinSuccessionUsage extends SuccessionMapUsage {
	public TwinSuccessionUsage(SuccessionAsUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
