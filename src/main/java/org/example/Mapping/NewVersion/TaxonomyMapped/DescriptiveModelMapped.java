package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Interfaces.BaseTaxonomy.DescriptiveModel;
import org.example.Mapping.Interfaces.TwinAction.Usage.ActionUsage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.Interfaces.TwinStateMachine.Usage.TwinStateMachineUsage;
import org.example.Mapping.Interfaces.TwinStrategy.Usage.StrategyUsage;
import org.example.Mapping.NewVersion.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinFlow.Usage.DescriptiveFlowUsageMapped;
import org.example.Mapping.NewVersion.TwinFlow.Usage.QueryFlowUsageMapped;
import org.example.Mapping.NewVersion.TwinStateMachine.Usage.TwinStateMachineUsageMapped;
import org.example.Mapping.NewVersion.TwinStrategy.Usage.TwinStrategyUsageMapped;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class DescriptiveModelMapped<T extends Type> extends MappedElement<T> implements DescriptiveModel {

	List<TwinActionUsageMapped<?>> derivedAttributes = new ArrayList<>();
	List<TwinStateMachineUsageMapped> descriptiveStateMachines = new ArrayList<>();
	List<TwinStrategyUsageMapped> descriptiveStrategies = new ArrayList<>();
	List<DescriptiveFlowUsageMapped> descriptiveFlows = new ArrayList<>();

	public DescriptiveModelMapped(T sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		derivedAttributes = context.mapSlot(this, "derivedAttributes", TwinActionUsageMapped.getRawClass());
		descriptiveStateMachines = context.mapSlot(this, "descriptiveStateMachine_", TwinStateMachineUsageMapped.class);
		descriptiveStrategies = context.mapSlot(this, "descriptiveStrategies", TwinStrategyUsageMapped.class);
		descriptiveFlows = context.mapSlot(this, "descriptiveFlows", DescriptiveFlowUsageMapped.class);
	}

	@Override
	public List<ActionUsage> getDerivedAttributes() {
		return new ArrayList<>(derivedAttributes);
	}

	@Override
	public List<TwinStateMachineUsage> getDescriptiveStateMachines() {
		return new ArrayList<>(descriptiveStateMachines);
	}

	@Override
	public List<StrategyUsage> getDescriptiveStrategies() {
		return new ArrayList<>(descriptiveStrategies);
	}

	@Override
	public List<FlowUsage> getDescriptiveFlows() {
		return new ArrayList<>(descriptiveFlows);
	}
}
