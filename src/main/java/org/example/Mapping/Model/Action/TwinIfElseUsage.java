package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.Predefined.MetaClasses.Action.IfElseMapUsage;
import Model.Predefined.MetaClasses.Action.EmptyActionCore;
import org.omg.sysml.lang.sysml.IfActionUsage;

@MappedMetaClass(value = IfActionUsage.class, core = EmptyActionCore.class)
public class TwinIfElseUsage extends IfElseMapUsage {
	public TwinIfElseUsage(IfActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
