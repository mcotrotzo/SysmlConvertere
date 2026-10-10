package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.Predefined.MetaClasses.Action.WhileMapUsage;
import Model.Predefined.MetaClasses.Action.EmptyActionCore;
import org.omg.sysml.lang.sysml.WhileLoopActionUsage;

@MappedMetaClass(value = WhileLoopActionUsage.class, core = EmptyActionCore.class)
public class TwinWhileUsage extends WhileMapUsage {
	public TwinWhileUsage(WhileLoopActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
