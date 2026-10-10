package org.example.Mapping.Model.Strategy;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Behavior;

@MappedLibrary(libraryName = "TwinStrategyLibrary::ExternalStrategy", core = ExternalStrategyCore.class)
public class ExternalStrategyDefinition extends TwinStrategyDefinition<ExternalStrategyCore> implements ExternalStrategyCoreApi {
	public ExternalStrategyDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
