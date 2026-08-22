package org.example.Mapping.Interfaces.BaseTaxonomy;


import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.PrescriptiveStrategy;

import java.util.List;

public interface PrescriptiveModel extends Taxonomy {

	List<PrescriptiveStrategy> getPrescriptiveStrategies();

	@Override
	default Context getContext() {
		return Context.PRESCRIPTIVE;
	}
}
