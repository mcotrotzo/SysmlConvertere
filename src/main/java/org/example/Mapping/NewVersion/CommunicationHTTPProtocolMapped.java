package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.HTTPProtocol;
import org.example.Mapping.Interfaces.TwinStringAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.HTTP_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationHTTPProtocolMapped extends CommunicationProtocolMapped implements HTTPProtocol {
	private Set<TwinStringMapped> url = new HashSet<>();


	public CommunicationHTTPProtocolMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinStringAttribute> getUrl() {
		return new ArrayList<>(url);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		url = new HashSet<>(context.mapSlot(this, "url", TwinStringMapped.class));
	}
}
