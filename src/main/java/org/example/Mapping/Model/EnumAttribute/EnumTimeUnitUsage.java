package org.example.Mapping.Model.EnumAttribute;




import Model.EmptyCore;import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.EnumTimeUnit;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinEnumLibrary::TimeUnit", core = EmptyCore.class)
public class EnumTimeUnitUsage extends EnumAttributeUsage<EnumTimeUnit> {
	public EnumTimeUnitUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	protected Class<EnumTimeUnit> getEnumClass() {
		return EnumTimeUnit.class;
	}
}
