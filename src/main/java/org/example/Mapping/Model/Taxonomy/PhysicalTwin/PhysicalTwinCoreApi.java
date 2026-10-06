package org.example.Mapping.Model.Taxonomy.PhysicalTwin;

import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCoreApi;
import org.example.Mapping.Model.Port.SensorUsage;
import org.example.Mapping.Model.Port.ActuatorUsage;
import org.example.Mapping.Model.Port.ConstPortUsage;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.example.Mapping.Model.Flow.PhysicalFlowUsage;

import java.util.List;

public interface PhysicalTwinCoreApi extends TaxonomyCoreApi<PhysicalTwinCore> {
	default List<SensorUsage> getSensors() { return getCore().getSensors(); }
	default List<ActuatorUsage> getActuators() { return getCore().getActuators(); }
	default List<TwinStateMachineUsage> getControlUnits() { return getCore().getControlUnits(); }
	default List<ConstPortUsage> getConstPorts() { return getCore().getConstPorts(); }
	default List<PhysicalFlowUsage> getPhysicalFlows() { return getCore().getPhysicalFlows(); }
}
