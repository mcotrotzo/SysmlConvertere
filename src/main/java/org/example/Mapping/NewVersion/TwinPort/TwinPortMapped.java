package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinPort.Protocol;
import org.example.Mapping.Interfaces.TwinPort.TwinPort;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MappedElementType(LibraryNameSpaces.TWIN_PORT)
@ToString(callSuper = true)
public class TwinPortMapped<Z extends TypeKind> extends MappedElement<Type, Z> implements TwinPort<Z> {
	private List<CommunicationProtocolMapped> protocols = new ArrayList<>();

	public TwinPortMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		protocols = context.mapSlot(this, "communicationProtocol", CommunicationProtocolMapped.class);
	}

	@Override
	public Optional<Protocol<Usage>> getProtocol() {
		return Optional.ofNullable(protocols.stream().findFirst().orElse(null));
	}

	@Override
	public List<TwinAttribute<Usage>> getAttributes() {
		return List.of();
	}

}
