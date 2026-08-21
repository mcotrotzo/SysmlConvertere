package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveModel;
import org.example.Mapping.Interfaces.PrescriptiveStrategy;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.PrescriptiveStrategyMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class PrescriptiveModelMapped<T extends Type> extends MappedElement<T> implements PrescriptiveModel {

	List<PrescriptiveStrategyMapped> prescriptiveStrategies = new ArrayList<>();

	public PrescriptiveModelMapped(T sysmlElement) {
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
