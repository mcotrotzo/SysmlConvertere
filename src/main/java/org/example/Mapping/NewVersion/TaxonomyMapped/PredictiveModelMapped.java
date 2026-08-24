package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PredictiveModel;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.Interfaces.TwinStrategy.Usage.StrategyUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinFlow.Definition.PredictiveFlowMapped;
import org.example.Mapping.NewVersion.TwinFlow.Usage.PredictiveFlowUsageMapped;
import org.example.Mapping.NewVersion.TwinStrategy.Usage.TwinStrategyUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.PREDICTIVE_MODEL)
@ToString(callSuper = true)
public class PredictiveModelMapped<T extends Type> extends MappedElement<T> implements PredictiveModel {

	List<TwinStrategyUsageMapped> predictiveStrategies = new ArrayList<>();
	List<PredictiveFlowUsageMapped> predictiveFlows = new ArrayList<>();
	public PredictiveModelMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		predictiveStrategies = context.mapSlot(this, "predictiveStrategies", TwinStrategyUsageMapped.class);
		predictiveFlows = context.mapSlot(this, "predictiveFlows", PredictiveFlowUsageMapped.class);
	}

	@Override
	public List<StrategyUsage> getPredictiveStrategies() {
		return new ArrayList<>(predictiveStrategies);
	}

	@Override
	public List<FlowUsage> getPredictiveFlows() {
		return new ArrayList<>(predictiveFlows);
	}

}
