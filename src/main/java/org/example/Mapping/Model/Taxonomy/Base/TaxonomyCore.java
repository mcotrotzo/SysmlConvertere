package org.example.Mapping.Model.Taxonomy.Base;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Core.Core;
import org.omg.sysml.lang.sysml.Type;

/** Base core of the taxonomy chain (Twin, PhysicalTwin, models, Shadow). */
public class TaxonomyCore extends Core<Type> {

	public TaxonomyCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
	}
}
