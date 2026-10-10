package org.example.Mapping.Model.Strategy;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.ActionUsage;

@MappedLibrary(libraryName = "TwinStrategyLibrary::ExternalStrategy", core = ExternalStrategyCore.class)
public class ExternalStrategyUsage extends TwinStrategyUsage<ExternalStrategyCore, ExternalStrategyDefinition> implements ExternalStrategyCoreApi {
	public ExternalStrategyUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
