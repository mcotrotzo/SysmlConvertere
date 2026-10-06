package org.example.Mapping.Model.EnumAttribute;

import org.example.Mapping.EnumOrderBy;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class EnumOrderByUsage extends EnumAttributeUsage<EnumOrderBy> {
	public EnumOrderByUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	protected Class<EnumOrderBy> getEnumClass() {
		return EnumOrderBy.class;
	}
}
