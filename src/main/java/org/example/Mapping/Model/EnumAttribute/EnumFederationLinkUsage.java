package org.example.Mapping.Model.EnumAttribute;




import Model.EmptyCore;import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.EnumFederationLink;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinEnumLibrary::FederatedLinkType", core = EmptyCore.class)
public class EnumFederationLinkUsage extends EnumAttributeUsage<EnumFederationLink> {
	public EnumFederationLinkUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	protected Class<EnumFederationLink> getEnumClass() {
		return EnumFederationLink.class;
	}
}
