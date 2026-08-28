package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveModel;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.PrescriptiveTaxonomyMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.PrescriptiveFlowMapped;
import org.example.Mapping.NewVersion.TwinStrategy.Definition.TwinStrategyMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class PrescriptiveModelMapped<T extends TypeKind>
		extends PrescriptiveTaxonomyMapped<T>
		implements PrescriptiveModel<T> {

	private CompartmentContainerMapped<TwinStrategyMapped<Usage>>
			prescriptiveStrategies;

	private CompartmentContainerMapped<PrescriptiveFlowMapped<Usage>>
			prescriptiveFlows;

	public PrescriptiveModelMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		prescriptiveStrategies = context.mapSlot(
				this,
				"prescriptiveStrategies",
				rawClassOf(TwinStrategyMapped.class)
		);

		prescriptiveFlows = context.mapSlot(
				this,
				"prescriptiveFlows",
				rawClassOf(PrescriptiveFlowMapped.class)
		);
	}

	@Override
	public CompartmentContainerMapped<TwinStrategyMapped<Usage>>
	getPrescriptiveStrategies() {
		return prescriptiveStrategies;
	}

	@Override
	public CompartmentContainerMapped<PrescriptiveFlowMapped<Usage>>
	getPrescriptiveFlows() {
		return prescriptiveFlows;
	}
}