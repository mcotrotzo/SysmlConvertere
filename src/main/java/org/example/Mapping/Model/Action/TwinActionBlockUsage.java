package org.example.Mapping.Model.Action;


import Model.Annotation.MappedLibrary;
import Mapper.Mapper;
import org.omg.sysml.lang.sysml.ActionUsage;


@MappedLibrary(libraryName = "TwinActionLibrary::TwinAction", core = ActionBlockCore.class)
public class TwinActionBlockUsage<C extends ActionBlockCore, D extends TwinActionBlockDefinition<?>> extends AbstractTwinActionUsage<C, D> implements ActionBlockCoreApi<C> {
	public TwinActionBlockUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
