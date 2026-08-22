package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwin;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.ActuatorUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.SensorUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.ControlUnitMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Mapping.NewVersion.TwinPort.Usage.ActuatorUsageMapped;
import org.example.Mapping.NewVersion.TwinPort.Usage.SensorUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.PHYSICAL_TWIN)
@ToString(callSuper = true)
public class PhysicalTwinMapped<T extends Type> extends MappedElement<T> implements PhysicalTwin {

	List<SensorUsageMapped> sensors = new ArrayList<>();
	List<ActuatorUsageMapped> actuators = new ArrayList<>();
	List<ControlUnitMapped> controlUnits = new ArrayList<>();
	List<TwinAttributeUsageMapped> constAttributes = new ArrayList<>();

	public PhysicalTwinMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		sensors = context.mapSlot(this, "sensors", SensorUsageMapped.class);
		actuators =context.mapSlot(this, "actuators", ActuatorUsageMapped.class);
		controlUnits = context.mapSlot(this, "controlUnit", ControlUnitMapped.class);
		constAttributes = context.mapSlot(this, "constAttributes", TwinAttributeUsageMapped.class);
	}
	@Override
	public List<SensorUsage> getSensors() {
		return new ArrayList<>(sensors);
	}

	@Override
	public List<ActuatorUsage> getActuators() {
		return new ArrayList<>(actuators);
	}

	@Override
	public List<ControlUnit> getControlUnits() {
		return new ArrayList<>(controlUnits);
	}

	@Override
	public List<TwinAttributeUsage> getConstAttributes() {
		return new ArrayList<>(constAttributes);
	}


}
