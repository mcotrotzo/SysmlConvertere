package org.example.Mapping.Model.EnumAttribute;

import org.example.Mapping.CustomStrategyType;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class EnumCustomStrategyTypeUsage extends EnumAttributeUsage<CustomStrategyType> {
	public EnumCustomStrategyTypeUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	protected Class<CustomStrategyType> getEnumClass() {
		return CustomStrategyType.class;
	}
}
