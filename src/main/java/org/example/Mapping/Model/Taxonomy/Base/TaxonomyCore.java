package org.example.Mapping.Model.Taxonomy.Base;

import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.omg.sysml.lang.sysml.Type;

/** Base core of the taxonomy chain (Twin, PhysicalTwin, models, Shadow). */
public class TaxonomyCore extends Core<Type> {

	public TaxonomyCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
	}
}
