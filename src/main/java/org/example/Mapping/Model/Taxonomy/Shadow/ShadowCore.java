package org.example.Mapping.Model.Taxonomy.Shadow;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Slots;
import lombok.Getter;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCore;
import org.example.Mapping.Model.Database.DatabaseUsage;
import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class ShadowCore extends TaxonomyCore {
	@Getter private List<DatabaseUsage<?, ?>> databases = List.of();

	public ShadowCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
		databases = mapper.mapSlot("databases", owner, Slots.<DatabaseUsage<?, ?>>rawClassOf(DatabaseUsage.class));
	}
}
