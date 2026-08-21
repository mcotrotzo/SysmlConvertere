package org.example.Mapping.Interfaces.FullTwin;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.FullTwinContext;

import java.util.Optional;

public interface Twin extends Taxonomy, FullTwinContext {

	Optional<PhysicalTwinUsage> getPhysicalTwin();
	Optional<ShadowUsage> getShadow();
	Optional<DescriptiveModelUsage> getDescriptiveModel();
	Optional<PredictiveModelUsage> getPredictiveModel();
	Optional<PrescriptiveModelUsage> getPrescriptiveModel();
}


