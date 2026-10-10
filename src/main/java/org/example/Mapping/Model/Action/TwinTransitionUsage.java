package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.Predefined.MetaClasses.Action.TransitionMapUsage;
import Model.Predefined.MetaClasses.Action.EmptyActionCore;
import org.omg.sysml.lang.sysml.TransitionUsage;

@MappedMetaClass(value = TransitionUsage.class, core = EmptyActionCore.class)
public class TwinTransitionUsage extends TransitionMapUsage {
	public TwinTransitionUsage(TransitionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
