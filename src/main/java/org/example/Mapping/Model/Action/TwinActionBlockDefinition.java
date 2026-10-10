package org.example.Mapping.Model.Action;


import Model.Annotation.MappedLibrary;
import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Behavior;


@MappedLibrary(libraryName = "TwinActionLibrary::TwinAction", core = ActionBlockCore.class)
public class TwinActionBlockDefinition<C extends ActionBlockCore> extends AbstractTwinActionDefinition<C> implements ActionBlockCoreApi<C> {
	public TwinActionBlockDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
