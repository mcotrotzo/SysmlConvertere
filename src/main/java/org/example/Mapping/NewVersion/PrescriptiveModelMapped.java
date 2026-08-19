package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.PrescriptiveModel;
import org.example.Mapping.Interfaces.PrescriptiveStrategy;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class PrescriptiveModelMapped extends MappedElement<Type> implements PrescriptiveModel {

	List<PrescriptiveStrategyMapped> prescriptiveStrategies = new ArrayList<>();

	public PrescriptiveModelMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<PrescriptiveStrategy> getPrescriptiveStrategies() {
		return new ArrayList<>(prescriptiveStrategies);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		prescriptiveStrategies = context.mapSlot(this, "prescriptiveStrategies", PrescriptiveStrategyMapped.class);
	}
}
