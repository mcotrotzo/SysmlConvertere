package org.example.Mapping.Interfaces;

import java.util.List;

public interface PhysicalTwin extends Model{

	List<Sensors> getSensors();

	List<Actuators> getActuators();

	List<ControlUnit> getControlUnits();

	List<TwinAttribute> getConstAttributes();
}
