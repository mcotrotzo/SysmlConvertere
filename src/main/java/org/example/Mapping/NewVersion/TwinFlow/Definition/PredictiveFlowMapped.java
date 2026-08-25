package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PREDICTIVE_FLOW)
public class PredictiveFlowMapped<T extends TypeKind> extends FlowMapped<T> {
	public PredictiveFlowMapped(Type sysmlElement) {
		super(sysmlElement);
	}

}
