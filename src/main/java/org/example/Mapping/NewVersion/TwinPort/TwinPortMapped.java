package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.Protocol;
import org.example.Mapping.Interfaces.TwinPort;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.CommunicationProtocolMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.*;

@MappedElementType(LibraryNameSpaces.TWIN_PORT)
@ToString(callSuper = true)
public abstract class TwinPortMapped extends MappedElement<Type> implements TwinPort {
	private List<CommunicationProtocolMapped> protocols = new ArrayList<>();

	public TwinPortMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		protocols = context.mapSlot(this, "communicationProtocol", CommunicationProtocolMapped.class);
	}

	@Override
	public Optional<Protocol> getProtocol() {
		return Optional.ofNullable(protocols.stream().findFirst().orElse(null));
	}

}
