package org.example.Mapping.Model.Strategy;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Action.TwinTriggerActionCore;
import org.example.Mapping.Model.Action.TwinTriggerActionUsage;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.omg.sysml.lang.sysml.ActionUsage;



@MappedLibrary(libraryName = "TwinStrategyLibrary::Strategy", core = TwinTriggerActionCore.class)
public class TwinStrategyUsage<C extends TwinTriggerActionCore, D extends TwinStrategyDefinition<?>> extends TwinTriggerActionUsage<C, D> {
	public TwinStrategyUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
