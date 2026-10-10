package org.example.Mapping.Model.Flow;




import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.EmptyCore;
import org.example.Mapping.Model.Action.TwinActionDefinition;
import org.omg.sysml.lang.sysml.Behavior;

@MappedLibrary(libraryName = "TwinActionLibrary::TwinFlow", core = EmptyCore.class)
public class TwinFlowDefinition extends TwinActionDefinition<EmptyCore> {
	public TwinFlowDefinition(Behavior sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}
