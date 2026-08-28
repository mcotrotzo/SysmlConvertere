package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.PredictiveModel;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.PredictiveTaxonomyMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.PredictiveFlowMapped;
import org.example.Mapping.NewVersion.TwinStrategy.Definition.TwinStrategyMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PREDICTIVE_MODEL)
@ToString(callSuper = true)
public class PredictiveModelMapped<T extends TypeKind>
		extends PredictiveTaxonomyMapped<T>
		implements PredictiveModel<T> {

	private CompartmentContainerMapped<TwinStrategyMapped<Usage>>
			predictiveStrategies;

	private CompartmentContainerMapped<PredictiveFlowMapped<Usage>>
			predictiveFlows;

	public PredictiveModelMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		predictiveStrategies = context.mapSlot(
				this,
				"predictiveStrategies",
				rawClassOf(TwinStrategyMapped.class)
		);

		predictiveFlows = context.mapSlot(
				this,
				"predictiveFlows",
				rawClassOf(PredictiveFlowMapped.class)
		);
	}

	@Override
	public CompartmentContainerMapped<TwinStrategyMapped<Usage>>
	getPredictiveStrategies() {
		return predictiveStrategies;
	}

	@Override
	public CompartmentContainerMapped<PredictiveFlowMapped<Usage>>
	getPredictiveFlows() {
		return predictiveFlows;
	}
}