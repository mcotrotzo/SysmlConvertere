package org.example.Mapping.Model.Port;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Usage;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::TwinPort", core = TwinPortCore.class)
public class TwinPortUsage<C extends TwinPortCore, D extends TwinPortDefinition<?>> extends Usage<C, Feature, D> implements TwinPortCoreApi<C> {
	public TwinPortUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
