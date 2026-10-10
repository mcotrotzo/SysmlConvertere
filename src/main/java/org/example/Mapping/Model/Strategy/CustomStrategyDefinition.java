package org.example.Mapping.Model.Strategy;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.omg.sysml.lang.sysml.Behavior;

@MappedLibrary(libraryName = "TwinStrategyLibrary::CustomStrategy", core = CustomStrategyCore.class)
public class CustomStrategyDefinition extends TwinStrategyDefinition<CustomStrategyCore> implements CustomStartegyCoreApi {
	public CustomStrategyDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
