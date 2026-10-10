package org.example.Mapping.Model.Twin;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.CloudTwinTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinDefLibrary::Twin", core = TwinCore.class)
public class TwinUsage extends CloudTwinTaxonomyUsage<TwinCore, TwinDefinition> implements TwinCoreApi {
	public TwinUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
