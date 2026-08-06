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
	Set<SensorMapped> sensors = new HashSet<>();
	Set<ActuatorMapped> actuators = new HashSet<>();
	Set<ControlUnitMapped> controlUnits = new HashSet<>();
	Set<TwinAttributeMapped> constAttributes = new HashSet<>();
	Set<TwinAttributeMapped> derivedAttributes = new HashSet<>();
	Set<QueryHistoryMapped> flatQueries = new HashSet<>();
	Set<GroupedHistoryQueryMapped> groupedQueries = new HashSet<>();
	Set<DescriptiveStateMachineMapped> descriptiveStateMachines = new HashSet<>();
	Set<DescriptiveStrategyMapped> descriptiveStrategies = new HashSet<>();
	Set<PredictiveStrategyMapped> predictiveStrategies = new HashSet<>();
	Set<PrescriptiveStrategyMapped> prescriptiveStrategies = new HashSet<>();
	Set<DatabaseMapped> databases = new HashSet<>();

	public MappedTwin(Type sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		sensors = new HashSet<>(context.mapSlot(this, "sensors", SensorMapped.class));
		actuators = new HashSet<>(context.mapSlot(this, "actuators", ActuatorMapped.class));
		controlUnits = new HashSet<>(context.mapSlot(this, "controlUnit", ControlUnitMapped.class));
		constAttributes = new HashSet<>(context.mapSlot(this, "constAttributes", TwinAttributeMapped.class));
		derivedAttributes = new HashSet<>(context.mapSlot(this, "derivedAttributes", TwinAttributeMapped.class));
		flatQueries = new HashSet<>(context.mapSlot(this, "queryHistory", QueryHistoryMapped.class));
		groupedQueries = new HashSet<>(context.mapSlot(this, "groupedQueryHistory", GroupedHistoryQueryMapped.class));
		descriptiveStateMachines = new HashSet<>(context.mapSlot(this, "descriptiveStateMachine_", DescriptiveStateMachineMapped.class));
		descriptiveStrategies = new HashSet<>(context.mapSlot(this, "descriptiveStrategies", DescriptiveStrategyMapped.class));
		predictiveStrategies = new HashSet<>(context.mapSlot(this, "predictiveStrategies", PredictiveStrategyMapped.class));
		prescriptiveStrategies = new HashSet<>(context.mapSlot(this, "prescriptiveStrategies", PrescriptiveStrategyMapped.class));
		databases = new HashSet<>(context.mapSlot(this, "databases", DatabaseMapped.class));
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
