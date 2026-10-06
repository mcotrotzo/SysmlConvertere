package org.example.Mapping.Model.EnumAttribute;

import org.example.Mapping.EnumFederationLink;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class EnumFederationLinkUsage extends EnumAttributeUsage<EnumFederationLink> {
	public EnumFederationLinkUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	protected Class<EnumFederationLink> getEnumClass() {
		return EnumFederationLink.class;
	}
}
