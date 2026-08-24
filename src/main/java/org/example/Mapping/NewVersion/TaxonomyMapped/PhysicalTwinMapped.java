package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwin;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.ActuatorUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.ConstPortUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.SensorUsage;
import org.example.Mapping.Interfaces.TwinStateMachine.Usage.TwinStateMachineUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinFlow.Definition.PhysicalFlowMapped;
import org.example.Mapping.NewVersion.TwinFlow.Usage.PhysicalFlowUsageMapped;
import org.example.Mapping.NewVersion.TwinPort.ConstPortMapped;
import org.example.Mapping.NewVersion.TwinPort.Usage.ConstPortUsageMapped;
import org.example.Mapping.NewVersion.TwinStateMachine.Usage.TwinStateMachineUsageMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Mapping.NewVersion.TwinPort.Usage.ActuatorUsageMapped;
import org.example.Mapping.NewVersion.TwinPort.Usage.SensorUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MappedElementType(LibraryNameSpaces.PHYSICAL_TWIN)
@ToString(callSuper = true)
public class PhysicalTwinMapped<T extends Type> extends MappedElement<T> implements PhysicalTwin {

	List<SensorUsageMapped> sensors = new ArrayList<>();
	List<ActuatorUsageMapped> actuators = new ArrayList<>();
	List<TwinStateMachineUsageMapped> controlUnits = new ArrayList<>();
	Optional<ConstPortUsageMapped> constAttributes =Optional.empty();
	List<PhysicalFlowUsageMapped> physicalFlows = new ArrayList<>();

	public PhysicalTwinMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		sensors = context.mapSlot(this, "sensors", SensorUsageMapped.class);
		actuators =context.mapSlot(this, "actuators", ActuatorUsageMapped.class);
		controlUnits = context.mapSlot(this, "controlUnit", TwinStateMachineUsageMapped.class);
		constAttributes = context.mapSlot(this, "constPort", ConstPortUsageMapped.class).stream().findFirst();
		physicalFlows = context.mapSlot(this, "physicalFlows", PhysicalFlowUsageMapped.class);
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
	public List<TwinStateMachineUsage> getControlUnits() {
		return new ArrayList<>(controlUnits);
	}

	@Override
	public List<FlowUsage> getPhysicalFlows() {
		return new ArrayList<>(physicalFlows);
	}

	@Override
	public Optional<ConstPortUsage> getConstPort() {
		return constAttributes.map(x -> (ConstPortUsage) x);
	}


}
