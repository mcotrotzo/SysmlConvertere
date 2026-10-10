package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.Annotation.MappedLibrary;
import Model.Predefined.MetaClasses.Action.ActionMapDefinition;
import org.omg.sysml.lang.sysml.Behavior;

@MappedLibrary(libraryName = "TwinActionLibrary::AbstractTwinAction", core = ActionBlockCore.class)
public class AbstractTwinActionDefinition<C extends ActionBlockCore> extends ActionMapDefinition<C> implements ActionBlockCoreApi<C> {
	public AbstractTwinActionDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
