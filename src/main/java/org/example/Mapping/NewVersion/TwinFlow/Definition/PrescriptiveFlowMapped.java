package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_FLOW)
public class PrescriptiveFlowMapped<T extends TypeKind> extends FlowMapped<T> {
	public PrescriptiveFlowMapped(Type sysmlElement) {
		super(sysmlElement);
	}

}
