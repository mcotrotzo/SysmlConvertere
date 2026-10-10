package org.example.Mapping.Model.Port;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Definition;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::TwinPort", core = TwinPortCore.class)
public class TwinPortDefinition<C extends TwinPortCore> extends Definition<C, Classifier> implements TwinPortCoreApi<C> {
	public TwinPortDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
