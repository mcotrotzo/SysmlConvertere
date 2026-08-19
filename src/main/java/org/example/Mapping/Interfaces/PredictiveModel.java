package org.example.Mapping.Interfaces;

import java.util.List;

public interface PredictiveModel extends Model {
	List<PredictiveStrategy> getPredictiveStrategies();
}
