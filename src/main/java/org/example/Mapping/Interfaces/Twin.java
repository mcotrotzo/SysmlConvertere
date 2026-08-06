package org.example.Mapping.Interfaces;

import java.util.List;

public interface Twin extends Model {
	List<Sensors> getSensors();

	List<Actuators> getActuators();

	List<ControlUnit> getControlUnits();

	List<TwinAttribute> getConstAttributes();

	List<TwinAttribute> getDerivedAttributes();

	List<QueryHistory> getQueriesHistory();

	List<GroupedHistoryQuery> getGroupQueriesHistory();

	List<DescriptiveStateMachine> getDescriptiveStateMachines();

	List<DescriptiveStrategy> getDescriptiveStrategies();

	List<PredictiveStrategy> getPredictiveStrategies();

	List<PrescriptiveStrategy> getPrescriptiveStrategies();

	List<Database> getDatabases();
}


