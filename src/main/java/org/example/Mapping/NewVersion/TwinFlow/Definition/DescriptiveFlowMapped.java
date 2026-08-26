package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;
import org.omg.sysml.lang.sysml.FlowUsage;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_FLOW)
public class DescriptiveFlowMapped<T extends TypeKind> extends FlowMapped<T> {


	public DescriptiveFlowMapped(FlowDefinition sysmlElement) {
		super(sysmlElement);
	}

	public DescriptiveFlowMapped(FlowUsage sysmlElement) {
		super(sysmlElement);
	}
}
