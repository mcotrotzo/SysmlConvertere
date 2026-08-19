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
import java.util.List;

@MappedElementType(LibraryNameSpaces.PHYSICAL_TWIN)
@ToString(callSuper = true)
public class PhysicalTwinMapped extends MappedElement<Type> implements PhysicalTwin {

	List<SensorMapped> sensors = new ArrayList<>();
	List<ActuatorMapped> actuators = new ArrayList<>();
	List<ControlUnitMapped> controlUnits = new ArrayList<>();
	List<TwinAttributeMapped> constAttributes = new ArrayList<>();

	public PhysicalTwinMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		sensors = context.mapSlot(this, "sensors", SensorMapped.class);
		actuators =context.mapSlot(this, "actuators", ActuatorMapped.class);
		controlUnits = context.mapSlot(this, "controlUnit", ControlUnitMapped.class);
		constAttributes = context.mapSlot(this, "constAttributes", TwinAttributeMapped.class);
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


}
