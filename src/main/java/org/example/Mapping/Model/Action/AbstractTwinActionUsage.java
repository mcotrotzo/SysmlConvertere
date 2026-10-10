package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.Predefined.MetaClasses.Action.ActionMapUsage;
import org.omg.sysml.lang.sysml.ActionUsage;


@MappedMetaClass(value = ActionUsage.class, core = ActionBlockCore.class)
public class AbstractTwinActionUsage<C extends ActionBlockCore, D extends AbstractTwinActionDefinition<?>> extends ActionMapUsage<C, ActionUsage, D> implements ActionBlockCoreApi<C> {
	public AbstractTwinActionUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
