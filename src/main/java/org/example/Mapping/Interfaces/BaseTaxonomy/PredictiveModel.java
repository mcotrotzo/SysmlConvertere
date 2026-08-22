package org.example.Mapping.Interfaces.BaseTaxonomy;


import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.PredictiveStrategy;

import java.util.List;

public interface PredictiveModel extends Taxonomy{
	List<PredictiveStrategy> getPredictiveStrategies();

	@Override
	default Context getContext() {
		return Context.PREDICTIVE;
	}
}
