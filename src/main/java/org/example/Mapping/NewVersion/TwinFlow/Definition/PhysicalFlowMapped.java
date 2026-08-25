package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PHYSICAL_FLOW)
public class PhysicalFlowMapped<T extends TypeKind> extends FlowMapped<T> {
	public PhysicalFlowMapped(Type sysmlElement) {
		super(sysmlElement);
	}

}
