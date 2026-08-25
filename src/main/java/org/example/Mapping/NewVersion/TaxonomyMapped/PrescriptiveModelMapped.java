package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveModel;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.PrescriptiveTaxonomyMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.PrescriptiveFlowMapped;
import org.example.Mapping.NewVersion.TwinStrategy.Definition.TwinStrategyMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class PrescriptiveModelMapped<T extends TypeKind> extends PrescriptiveTaxonomyMapped<T> implements PrescriptiveModel<T> {

	List<TwinStrategyMapped<Usage>> prescriptiveStrategies = new ArrayList<>();
	List<PrescriptiveFlowMapped<Usage>> prescriptiveFlows = new ArrayList<>();

	public PrescriptiveModelMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<Strategy<Usage>> getPrescriptiveStrategies() {
		return new ArrayList<>(prescriptiveStrategies);
	}

	@Override
	public List<Flow<Usage>> getPrescriptiveFlows() {
		return new ArrayList<>(prescriptiveFlows);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		prescriptiveStrategies = context.mapSlot(this, "prescriptiveStrategies", rawClassOf(TwinStrategyMapped.class));
		prescriptiveFlows = context.mapSlot(this, "prescriptiveFlows", rawClassOf(PrescriptiveFlowMapped.class));
	}
}
