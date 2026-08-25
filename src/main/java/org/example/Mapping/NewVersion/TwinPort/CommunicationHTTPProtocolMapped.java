package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;
import org.example.Mapping.Interfaces.TwinPort.HTTPProtocol;
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
	private List<TwinBaseAttributeStringMapped<Usage>> url = new ArrayList<>();


	public CommunicationHTTPProtocolMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinBaseString<Usage>> getUrl() {
		return new ArrayList<>(url);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		url = context.mapAttributes(this, "url", TwinBaseAttributeStringMapped.getRawStringUsageClass(), Role.CONFIG);
	}
}
