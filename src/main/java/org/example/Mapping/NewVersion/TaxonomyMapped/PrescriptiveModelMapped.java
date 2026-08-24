package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveModel;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.Interfaces.TwinStrategy.Usage.StrategyUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinFlow.Usage.PrescriptiveFlowUsageMapped;
import org.example.Mapping.NewVersion.TwinStrategy.Usage.TwinStrategyUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class PrescriptiveModelMapped<T extends Type> extends MappedElement<T> implements PrescriptiveModel {

	List<TwinStrategyUsageMapped> prescriptiveStrategies = new ArrayList<>();
	List<PrescriptiveFlowUsageMapped> prescriptiveFlows = new ArrayList<>();
	public PrescriptiveModelMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<StrategyUsage> getPrescriptiveStrategies() {
		return new ArrayList<>(prescriptiveStrategies);
	}

	@Override
	public List<FlowUsage> getPrescriptiveFlows() {
		return new ArrayList<>(prescriptiveFlows);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		prescriptiveStrategies = context.mapSlot(this, "prescriptiveStrategies", TwinStrategyUsageMapped.class);
		prescriptiveFlows = context.mapSlot(this, "prescriptiveFlows", PrescriptiveFlowUsageMapped.class);
	}
}
