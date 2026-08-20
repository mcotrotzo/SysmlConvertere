package org.example.Mapping.Interfaces;

import java.util.List;

public interface DescriptiveModel extends Model {

	List<TwinAttribute> getDerivedAttributes();

	List<DescriptiveStateMachine> getDescriptiveStateMachines();

	List<DescriptiveStrategy> getDescriptiveStrategies();

}
