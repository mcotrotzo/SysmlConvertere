package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.DescriptiveContext;
import org.example.Mapping.Interfaces.DescriptiveStateMachine;
import org.example.Mapping.Interfaces.DescriptiveStrategy;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;

public interface DescriptiveModel extends DescriptiveContext,Taxonomy {

	List<TwinAttributeUsage> getDerivedAttributes();

	List<DescriptiveStateMachine> getDescriptiveStateMachines();

	List<DescriptiveStrategy> getDescriptiveStrategies();

}
