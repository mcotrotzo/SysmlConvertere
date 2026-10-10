package org.example.Mapping.Model.EnumAttribute;




import Model.EmptyCore;import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.EnumOrderBy;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinEnumLibrary::ORDER_BY", core = EmptyCore.class)
public class EnumOrderByUsage extends EnumAttributeUsage<EnumOrderBy> {
	public EnumOrderByUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	protected Class<EnumOrderBy> getEnumClass() {
		return EnumOrderBy.class;
	}
}
