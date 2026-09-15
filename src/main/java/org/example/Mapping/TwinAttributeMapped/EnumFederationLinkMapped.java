package org.example.Mapping.TwinAttributeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumFederationLink;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
@MappedElementType(LibraryNameSpaces.FEDERATION_LINK_TYPE)
@ToString(callSuper = true)
public class EnumFederationLinkMapped extends EnumAttribute<EnumFederationLink>{


	protected EnumFederationLinkMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	protected Class<EnumFederationLink> getEnumClass() {
		return EnumFederationLink.class;
	}
}
