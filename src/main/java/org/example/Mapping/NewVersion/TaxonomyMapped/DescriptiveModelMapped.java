package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Interfaces.BaseTaxonomy.DescriptiveModel;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public abstract class DescriptiveModelMapped<T extends Type> extends MappedElement<T> implements DescriptiveModel {

	List<TwinAttributeUsageMapped> derivedAttributes = new ArrayList<>();
	List<QueryHistoryMapped> flatQueries = new ArrayList<>();
	List<GroupedHistoryQueryMapped> groupedQueries = new ArrayList<>();
	List<DescriptiveStateMachineMapped> descriptiveStateMachines = new ArrayList<>();
	List<DescriptiveStrategyMapped> descriptiveStrategies = new ArrayList<>();

	public DescriptiveModelMapped(T sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		derivedAttributes = context.mapSlot(this, "derivedAttributes", TwinAttributeUsageMapped.class);
		descriptiveStateMachines = context.mapSlot(this, "descriptiveStateMachine_", DescriptiveStateMachineMapped.class);
		descriptiveStrategies = context.mapSlot(this, "descriptiveStrategies", DescriptiveStrategyMapped.class);
	}

	@Override
	public List<TwinAttributeUsage> getDerivedAttributes() {
		return new ArrayList<>(derivedAttributes);
	}

	@Override
	public List<DescriptiveStateMachine> getDescriptiveStateMachines() {
		return new ArrayList<>(descriptiveStateMachines);
	}

	@Override
	public List<DescriptiveStrategy> getDescriptiveStrategies() {
		return new ArrayList<>(descriptiveStrategies);
	}
}
