package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class DescriptiveModelMapped extends MappedElement<Type> implements DescriptiveModel {

	List<TwinAttributeMapped> derivedAttributes = new ArrayList<>();
	List<QueryHistoryMapped> flatQueries = new ArrayList<>();
	List<GroupedHistoryQueryMapped> groupedQueries = new ArrayList<>();
	List<DescriptiveStateMachineMapped> descriptiveStateMachines = new ArrayList<>();
	List<DescriptiveStrategyMapped> descriptiveStrategies = new ArrayList<>();

	public DescriptiveModelMapped(Type sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		derivedAttributes = context.mapSlot(this, "derivedAttributes", TwinAttributeMapped.class);
		flatQueries = context.mapSlot(this, "queryHistory", QueryHistoryMapped.class);
		groupedQueries = context.mapSlot(this, "groupedQueryHistory", GroupedHistoryQueryMapped.class);
		descriptiveStateMachines = context.mapSlot(this, "descriptiveStateMachine_", DescriptiveStateMachineMapped.class);
		descriptiveStrategies = context.mapSlot(this, "descriptiveStrategies", DescriptiveStrategyMapped.class);
	}

	@Override
	public List<TwinAttribute> getDerivedAttributes() {
		return new ArrayList<>(derivedAttributes);
	}

	@Override
	public List<GroupedHistoryQuery> getGroupQueriesHistory() {
		return new ArrayList<>(groupedQueries);
	}

	@Override
	public List<QueryHistory> getQueriesHistory() {
		return new ArrayList<>(flatQueries);
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
