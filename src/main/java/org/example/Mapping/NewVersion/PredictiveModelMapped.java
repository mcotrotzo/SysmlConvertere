package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.PredictiveModel;
import org.example.Mapping.Interfaces.PredictiveStrategy;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.PREDICTIVE_MODEL)
@ToString(callSuper = true)
public class PredictiveModelMapped extends MappedElement<Type> implements PredictiveModel {

	List<PredictiveStrategyMapped> predictiveStrategies = new ArrayList<>();

	public PredictiveModelMapped(Type sysmlElement) {
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
