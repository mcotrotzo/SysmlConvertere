package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.DescriptiveModel;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.DescriptiveTaxonomyMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.DescriptiveFlowMapped;
import org.example.Mapping.NewVersion.TwinStateMachine.Definition.TwinStateMachineMapped;
import org.example.Mapping.NewVersion.TwinStrategy.Definition.TwinStrategyMapped;
import org.example.Mapping.TwinAction.TwinActionMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class DescriptiveModelMapped<T extends TypeKind> extends DescriptiveTaxonomyMapped<T> implements DescriptiveModel<T> {

	private CompartmentContainerMapped<TwinActionMapped<?, Usage>> derivedAttributes;
	private CompartmentContainerMapped<TwinStateMachineMapped<Usage>> descriptiveStateMachines;
	private CompartmentContainerMapped<TwinStrategyMapped<Usage>> descriptiveStrategies;
	private CompartmentContainerMapped<DescriptiveFlowMapped<Usage>> descriptiveFlows;

	public DescriptiveModelMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		derivedAttributes = context.mapSlot(this, "derivedAttributes", TwinActionMapped.getActionMappedUsageClass());
		descriptiveStateMachines = context.mapSlot(this, "descriptiveStateMachine_", rawClassOf(TwinStateMachineMapped.class));
		descriptiveStrategies = context.mapSlot(this, "descriptiveStrategies", rawClassOf(TwinStrategyMapped.class));
		descriptiveFlows = context.mapSlot(this, "descriptiveFlows", rawClassOf(DescriptiveFlowMapped.class));
	}

	@Override
	public CompartmentContainer<? extends Action<Usage>> getDerivedAttributes() {
		return derivedAttributes;
	}

	@Override
	public CompartmentContainer<? extends TwinStateMachine<Usage>> getDescriptiveStateMachines() {
		return descriptiveStateMachines;
	}

	@Override
	public CompartmentContainer<? extends Strategy<Usage>> getDescriptiveStrategies() {
		return descriptiveStrategies;
	}

	@Override
	public CompartmentContainer<? extends Flow<Usage>> getDescriptiveFlows() {
		return descriptiveFlows;
	}
}