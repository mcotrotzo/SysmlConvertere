package org.example.Mapping.Interfaces.FullTwin;

import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;

import java.util.Optional;

public interface Twin extends Taxonomy {

	Optional<PhysicalTwinUsage> getPhysicalTwin();
	Optional<ShadowUsage> getShadow();
	Optional<DescriptiveModelUsage> getDescriptiveModel();
	Optional<PredictiveModelUsage> getPredictiveModel();
	Optional<PrescriptiveModelUsage> getPrescriptiveModel();
}


