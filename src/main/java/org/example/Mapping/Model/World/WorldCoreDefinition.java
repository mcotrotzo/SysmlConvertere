package org.example.Mapping.Model.World;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Definition;
import org.omg.sysml.lang.sysml.Classifier;

import java.util.SequencedCollection;

@MappedLibrary(libraryName = "TwinDefLibrary::World", core = WorldCore.class)
public class WorldCoreDefinition extends Definition<WorldCore, Classifier> implements WorldCoreApi {
	public WorldCoreDefinition(Classifier sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}


}
