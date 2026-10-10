package org.example.Mapping.Model.Port;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::ConstPort", core = ConstPortCore.class)
public class ConstPortDefinition extends TwinPortDefinition<ConstPortCore> implements ConstPortCoreApi {
	public ConstPortDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
