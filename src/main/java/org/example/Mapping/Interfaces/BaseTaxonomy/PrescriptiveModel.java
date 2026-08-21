package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.PrescriptiveContext;
import org.example.Mapping.Interfaces.PrescriptiveStrategy;

import java.util.List;

public interface PrescriptiveModel extends Taxonomy,PrescriptiveContext {

	List<PrescriptiveStrategy> getPrescriptiveStrategies();

}
