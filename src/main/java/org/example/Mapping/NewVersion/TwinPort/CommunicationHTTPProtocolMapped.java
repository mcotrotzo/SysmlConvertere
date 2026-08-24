package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseStringUsage;
import org.example.Mapping.Interfaces.TwinPort.HTTPProtocol;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinStringMappedUsage;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.HTTP_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationHTTPProtocolMapped extends CommunicationProtocolMapped implements HTTPProtocol {
	private List<TwinStringMappedUsage> url = new ArrayList<>();


	public CommunicationHTTPProtocolMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinBaseStringUsage> getUrl() {
		return new ArrayList<>(url);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		url = context.mapAttributes(this, "url", TwinStringMappedUsage.class, Role.CONFIG);
	}
}
