package org.example.Mapping.Model.Action;


import Mapper.Mapper;
import Model.Core.Core;
import Model.Usage;
import org.omg.sysml.lang.sysml.ActionUsage;



public abstract class TwinActionUsage<C extends Core<? super U>, U extends ActionUsage, D extends TwinActionDefinition<?>> extends Usage<C, U, D> {
	protected TwinActionUsage(U sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
