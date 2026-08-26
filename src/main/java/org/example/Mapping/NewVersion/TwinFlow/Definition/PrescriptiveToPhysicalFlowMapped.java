package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;
import org.omg.sysml.lang.sysml.FlowUsage;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_TO_PHYSICAL_FLOW)
public class PrescriptiveToPhysicalFlowMapped<T extends TypeKind> extends FlowMapped<T> {

	public PrescriptiveToPhysicalFlowMapped(FlowDefinition sysmlElement) {
		super(sysmlElement);
	}

	public PrescriptiveToPhysicalFlowMapped(FlowUsage sysmlElement) {
		super(sysmlElement);
	}
}
