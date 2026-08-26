package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;
import org.omg.sysml.lang.sysml.FlowUsage;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_FLOW)
public class PrescriptiveFlowMapped<T extends TypeKind> extends FlowMapped<T> {

	public PrescriptiveFlowMapped(FlowDefinition sysmlElement) {
		super(sysmlElement);
	}

	public PrescriptiveFlowMapped(FlowUsage sysmlElement) {
		super(sysmlElement);
	}
}
