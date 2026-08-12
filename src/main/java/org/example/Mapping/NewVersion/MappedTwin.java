package org.example.Mapping.NewVersion;


import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.ActuatorMapped;
import org.example.Mapping.NewVersion.TwinPort.SensorMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(value = LibraryNameSpaces.TWIN)
@ToString(callSuper = true)
public class MappedTwin extends MappedElement<Type> implements Twin {
	List<SensorMapped> sensors = new ArrayList<>();
	List<ActuatorMapped> actuators = new ArrayList<>();
	List<ControlUnitMapped> controlUnits = new ArrayList<>();
	List<TwinAttributeMapped> constAttributes = new ArrayList<>();
	List<TwinAttributeMapped> derivedAttributes = new ArrayList<>();
	List<QueryHistoryMapped> flatQueries = new ArrayList<>();
	List<GroupedHistoryQueryMapped> groupedQueries = new ArrayList<>();
	List<DescriptiveStateMachineMapped> descriptiveStateMachines = new ArrayList<>();
	List<DescriptiveStrategyMapped> descriptiveStrategies = new ArrayList<>();
	List<PredictiveStrategyMapped> predictiveStrategies = new ArrayList<>();
	List<PrescriptiveStrategyMapped> prescriptiveStrategies = new ArrayList<>();
	List<DatabaseMapped> databases = new ArrayList<>();

	public MappedTwin(Type sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		sensors = context.mapSlot(this, "sensors", SensorMapped.class);
		actuators =context.mapSlot(this, "actuators", ActuatorMapped.class);
		controlUnits = context.mapSlot(this, "controlUnit", ControlUnitMapped.class);
		constAttributes = context.mapSlot(this, "constAttributes", TwinAttributeMapped.class);
		derivedAttributes = context.mapSlot(this, "derivedAttributes", TwinAttributeMapped.class);
		flatQueries = context.mapSlot(this, "queryHistory", QueryHistoryMapped.class);
		groupedQueries = context.mapSlot(this, "groupedQueryHistory", GroupedHistoryQueryMapped.class);
		descriptiveStateMachines = context.mapSlot(this, "descriptiveStateMachine_", DescriptiveStateMachineMapped.class);
		descriptiveStrategies = context.mapSlot(this, "descriptiveStrategies", DescriptiveStrategyMapped.class);
		predictiveStrategies = context.mapSlot(this, "predictiveStrategies", PredictiveStrategyMapped.class);
		prescriptiveStrategies = context.mapSlot(this, "prescriptiveStrategies", PrescriptiveStrategyMapped.class);
		databases = context.mapSlot(this, "databases", DatabaseMapped.class);
	}

	@Override
	public List<Sensors> getSensors() {
		return new ArrayList<>(sensors);
	}

	@Override
	public List<Actuators> getActuators() {
		return new ArrayList<>(actuators);
	}

	@Override
	public List<ControlUnit> getControlUnits() {
		return new ArrayList<>(controlUnits);
	}

	@Override
	public List<TwinAttribute> getConstAttributes() {
		return new ArrayList<>(constAttributes);
	}

	@Override
	public List<TwinAttribute> getDerivedAttributes() {
		return new ArrayList<>(derivedAttributes);
	}

	@Override
	public List<GroupedHistoryQuery> getGroupQueriesHistory() {
		return new ArrayList<>(groupedQueries);
	}

	@Override
	public List<QueryHistory> getQueriesHistory() {
		return new ArrayList<>(flatQueries);
	}

	@Override
	public List<DescriptiveStateMachine> getDescriptiveStateMachines() {
		return new ArrayList<>(descriptiveStateMachines);
	}

	@Override
	public List<DescriptiveStrategy> getDescriptiveStrategies() {
		return new ArrayList<>(descriptiveStrategies);
	}

	@Override
	public List<PredictiveStrategy> getPredictiveStrategies() {
		return new ArrayList<>(predictiveStrategies);
	}

	@Override
	public List<PrescriptiveStrategy> getPrescriptiveStrategies() {
		return new ArrayList<>(prescriptiveStrategies);
	}

	@Override
	public List<Database> getDatabases() {
		return new ArrayList<>(databases);
	}


}
