package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.PhysicalContext;
import org.example.Mapping.Interfaces.ControlUnit;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.ActuatorUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.SensorUsage;

import java.util.List;

public interface PhysicalTwin extends PhysicalContext,Taxonomy {

	List<SensorUsage> getSensors();

	List<ActuatorUsage> getActuators();

	List<ControlUnit> getControlUnits();

	List<TwinAttributeUsage> getConstAttributes();
}
