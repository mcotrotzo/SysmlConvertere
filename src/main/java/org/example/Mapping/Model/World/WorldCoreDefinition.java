package org.example.Mapping.Model.World;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Classifier;

import java.util.SequencedCollection;
import java.util.function.Supplier;

public class WorldCoreDefinition extends Definition<WorldCore, Classifier> implements WorldCoreApi {
	public WorldCoreDefinition(Classifier sysmlElement, Supplier<WorldCore> coreFactory, Mapper newMappe) {
		super(sysmlElement, coreFactory, newMappe);
	}


}
