package org.example.Mapping.Model.World;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;

import java.util.function.Supplier;

public class WorldCoreUsage extends Usage<WorldCore, Feature, WorldCoreDefinition> implements WorldCoreApi {

	public WorldCoreUsage(Feature sysmlElement, Supplier<WorldCore> coreFactory, Mapper newMappe) {
		super(sysmlElement, coreFactory, newMappe, WorldCoreDefinition.class);
	}
}
