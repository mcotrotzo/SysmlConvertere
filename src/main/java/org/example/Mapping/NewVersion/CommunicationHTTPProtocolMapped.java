package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.HTTPProtocol;
import org.example.Mapping.Interfaces.TwinStringAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.HTTP_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationHTTPProtocolMapped extends CommunicationProtocolMapped implements HTTPProtocol {
	private List<TwinStringMappedUsage> url = new ArrayList<>();


	public CommunicationHTTPProtocolMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinStringAttributeUsage> getUrl() {
		return new ArrayList<>(url);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		url = context.mapSlot(this, "url", TwinStringMappedUsage.class);
	}
}
