package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinPort.Protocol;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.COMMUNICATION_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationProtocolMapped<T extends TypeKind> extends MappedElement<Type, T> implements Protocol<T> {

	public CommunicationProtocolMapped(Type sysmlElement) {
		super(sysmlElement);
	}


}
