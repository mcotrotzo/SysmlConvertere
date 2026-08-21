package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PredictiveModel;
import org.example.Mapping.Interfaces.PredictiveStrategy;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.PredictiveStrategyMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.PREDICTIVE_MODEL)
@ToString(callSuper = true)
public class PredictiveModelMapped<T extends Type> extends MappedElement<T> implements PredictiveModel {

	List<PredictiveStrategyMapped> predictiveStrategies = new ArrayList<>();

	public PredictiveModelMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		predictiveStrategies = context.mapSlot(this, "predictiveStrategies", PredictiveStrategyMapped.class);
	}

	@Override
	public List<PredictiveStrategy> getPredictiveStrategies() {
		return new ArrayList<>(predictiveStrategies);
	}

}
