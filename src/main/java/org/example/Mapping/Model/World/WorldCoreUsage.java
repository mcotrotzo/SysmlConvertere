package org.example.Mapping.Model.World;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Usage;
import org.omg.sysml.lang.sysml.Feature;


@MappedLibrary(libraryName = "TwinDefLibrary::World", core = WorldCore.class)
public class WorldCoreUsage extends Usage<WorldCore, Feature, WorldCoreDefinition> implements WorldCoreApi {

	public WorldCoreUsage(Feature sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}
