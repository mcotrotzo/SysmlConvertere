package org.example.Mapping.Model.Port;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::ConstPort", core = ConstPortCore.class)
public class ConstPortUsage extends TwinPortUsage<ConstPortCore, ConstPortDefinition> implements ConstPortCoreApi {
	public ConstPortUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
