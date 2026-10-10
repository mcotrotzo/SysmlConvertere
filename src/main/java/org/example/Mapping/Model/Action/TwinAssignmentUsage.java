package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.Predefined.MetaClasses.Action.AssignmentMapUsage;
import Model.Predefined.MetaClasses.Action.EmptyActionCore;
import Model.Slots;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.omg.sysml.lang.sysml.AssignmentActionUsage;

@MappedMetaClass(value = AssignmentActionUsage.class, core = EmptyActionCore.class)
public class TwinAssignmentUsage extends AssignmentMapUsage {

	private static final Class<TwinAttributeUsage<?, ?>> ATTRIBUTE = Slots.rawClassOf(TwinAttributeUsage.class);

	public TwinAssignmentUsage(AssignmentActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	// twins only assign to twin attributes; mapChain rejects anything else while mapping
	@Override
	protected void mapReferent() {
		referent = instance.mapChain(referentChain(), this, ATTRIBUTE);
	}

	@Override
	public TwinAttributeUsage<?, ?> getReferent() {
		return ATTRIBUTE.cast(referent);
	}
}
