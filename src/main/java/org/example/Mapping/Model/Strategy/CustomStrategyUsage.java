package org.example.Mapping.Model.Strategy;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.omg.sysml.lang.sysml.ActionUsage;

@MappedLibrary(libraryName = "TwinStrategyLibrary::CustomStrategy", core = CustomStrategyCore.class)
public class CustomStrategyUsage extends TwinStrategyUsage<CustomStrategyCore, CustomStrategyDefinition> implements CustomStartegyCoreApi {
	public CustomStrategyUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
