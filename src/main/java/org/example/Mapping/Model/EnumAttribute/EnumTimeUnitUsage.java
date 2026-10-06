package org.example.Mapping.Model.EnumAttribute;

import org.example.Mapping.EnumTimeUnit;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class EnumTimeUnitUsage extends EnumAttributeUsage<EnumTimeUnit> {
	public EnumTimeUnitUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	protected Class<EnumTimeUnit> getEnumClass() {
		return EnumTimeUnit.class;
	}
}
