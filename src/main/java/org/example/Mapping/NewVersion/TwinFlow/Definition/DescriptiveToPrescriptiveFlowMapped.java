package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;
import org.omg.sysml.lang.sysml.FlowUsage;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_TO_PRESCRIPTIVE_FLOW)
public class DescriptiveToPrescriptiveFlowMapped<T extends TypeKind> extends FlowMapped<T> {

	public DescriptiveToPrescriptiveFlowMapped(FlowDefinition sysmlElement) {
		super(sysmlElement);
	}

	public DescriptiveToPrescriptiveFlowMapped(FlowUsage sysmlElement) {
		super(sysmlElement);
	}
}
