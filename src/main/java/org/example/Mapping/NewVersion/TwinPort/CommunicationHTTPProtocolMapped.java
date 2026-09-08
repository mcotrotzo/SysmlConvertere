package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;
import org.example.Mapping.Interfaces.TwinPort.HTTPProtocol;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeStringMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.HTTP_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationHTTPProtocolMapped<T extends TypeKind> extends CommunicationProtocolMapped<T> implements HTTPProtocol<T> {
	private CompartmentMapped<TwinBaseAttributeStringMapped<Usage>> url;


	public CommunicationHTTPProtocolMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public Compartment<? extends TwinBaseString<Usage>> getUrl() {
		return url;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		url = context.mapSlot(this, "url", TwinBaseAttributeStringMapped.getRawStringUsageClass()).getCompartment().stream().findFirst().orElseThrow(() -> new MappingException("HTTPProtocol '%s': slot 'url' is required but not found.".formatted(getName())));
	}
}
