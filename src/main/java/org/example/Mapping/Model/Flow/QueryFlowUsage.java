package org.example.Mapping.Model.Flow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.CloudTwinTaxonomyUsage;
import org.example.Mapping.Model.Taxonomy.Base.PhysicalTaxonomyUsage;

import lombok.Getter;

import org.example.Mapping.Model.Slots;
import org.example.Mapping.Model.Attribute.TwinAttributeIntegerUsage;
import org.example.Mapping.Model.EnumAttribute.EnumOrderByUsage;
import org.example.Mapping.Model.EnumAttribute.EnumTimeUnitUsage;
import org.omg.sysml.lang.sysml.FlowUsage;

import java.util.Optional;

public class QueryFlowUsage extends TwinFlowUsage {

	@Getter private Optional<TwinAttributeIntegerUsage> since = Optional.empty();
	@Getter private Optional<EnumTimeUnitUsage> sinceUnit = Optional.empty();
	@Getter private Optional<EnumOrderByUsage> orderBy = Optional.empty();
	@Getter private Optional<TwinAttributeIntegerUsage> limit = Optional.empty();

	public QueryFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		since = Slots.atMostOne(this, "since", instance.mapSlot("since", this, TwinAttributeIntegerUsage.class));
		sinceUnit = Slots.atMostOne(this, "sinceUnit", instance.mapSlot("sinceUnit", this, EnumTimeUnitUsage.class));
		orderBy = Slots.atMostOne(this, "orderBy", instance.mapSlot("orderBy", this, EnumOrderByUsage.class));
		limit = Slots.atMostOne(this, "limit", instance.mapSlot("limit", this, TwinAttributeIntegerUsage.class));
	}

	@Override
	public Class<PhysicalTaxonomyUsage> getSourceTaxonomy() {
		return PhysicalTaxonomyUsage.class;
	}

	@Override
	public Class<CloudTwinTaxonomyUsage> getTargetTaxonomy() {
		return CloudTwinTaxonomyUsage.class;
	}
}
