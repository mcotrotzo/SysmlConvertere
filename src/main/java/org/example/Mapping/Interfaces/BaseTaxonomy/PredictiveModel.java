package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.PredictiveContext;
import org.example.Mapping.Interfaces.PredictiveStrategy;

import java.util.List;

public interface PredictiveModel extends PredictiveContext,Taxonomy {
	List<PredictiveStrategy> getPredictiveStrategies();
}
