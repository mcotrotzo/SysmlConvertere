package org.example.Mapping.Model.EnumAttribute;




import Model.EmptyCore;import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.CustomStrategyType;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinEnumLibrary::CustomStrategyType", core = EmptyCore.class)
public class EnumCustomStrategyTypeUsage extends EnumAttributeUsage<CustomStrategyType> {
	public EnumCustomStrategyTypeUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	protected Class<CustomStrategyType> getEnumClass() {
		return CustomStrategyType.class;
	}
}
