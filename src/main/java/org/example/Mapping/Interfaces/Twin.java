package org.example.Mapping.Interfaces;

import java.util.List;
import java.util.Optional;

public interface Twin extends Model {

	Optional<PhysicalTwin> getPhysicalTwin();
	Optional<Shadow> getShadow();
	Optional<DescriptiveModel> getDescriptiveModel();
	Optional<PredictiveModel> getPredictiveModel();
	Optional<PrescriptiveModel> getPrescriptiveModel();
}


