package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwin;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinPort.Actuators;
import org.example.Mapping.Interfaces.TwinPort.ConstPort;
import org.example.Mapping.Interfaces.TwinPort.Sensors;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.PhysicalTwinTaxonomyMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.PhysicalFlowMapped;
import org.example.Mapping.NewVersion.TwinPort.ActuatorMapped;
import org.example.Mapping.NewVersion.TwinPort.ConstPortMapped;
import org.example.Mapping.NewVersion.TwinPort.SensorMapped;
import org.example.Mapping.NewVersion.TwinStateMachine.Definition.TwinStateMachineMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MappedElementType(LibraryNameSpaces.PHYSICAL_TWIN)
@ToString(callSuper = true)
public class PhysicalTwinMapped<T extends TypeKind> extends PhysicalTwinTaxonomyMapped<T> implements PhysicalTwin<T> {

	List<SensorMapped<Usage>> sensors = new ArrayList<>();
	List<ActuatorMapped<Usage>> actuators = new ArrayList<>();
	List<TwinStateMachineMapped<Usage>> controlUnits = new ArrayList<>();
	Optional<ConstPortMapped<Usage>> constAttributes = Optional.empty();
	List<PhysicalFlowMapped<Usage>> physicalFlows = new ArrayList<>();

	public PhysicalTwinMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		sensors = context.mapSlot(this, "sensors", rawClassOf(SensorMapped.class));
		actuators = context.mapSlot(this, "actuators", rawClassOf(ActuatorMapped.class));
		controlUnits = context.mapSlot(this, "controlUnit", rawClassOf(TwinStateMachineMapped.class));
		Class<ConstPortMapped<Usage>> rawClassOfConstPort = rawClassOf(ConstPortMapped.class);
		constAttributes = context.mapSlot(this, "constPort", rawClassOfConstPort).stream().findFirst();
		physicalFlows = context.mapSlot(this, "physicalFlows", rawClassOf(PhysicalFlowMapped.class));
	}

	@Override
	public List<Sensors<Usage>> getSensors() {
		return new ArrayList<>(sensors);
	}

	@Override
	public List<Actuators<Usage>> getActuators() {
		return new ArrayList<>(actuators);
	}

	@Override
	public List<TwinStateMachine<Usage>> getControlUnits() {
		return new ArrayList<>(controlUnits);
	}

	@Override
	public List<Flow<Usage>> getPhysicalFlows() {
		return new ArrayList<>(physicalFlows);
	}

	@Override
	public Optional<ConstPort<Usage>> getConstPort() {
		return constAttributes.map(x -> x);
	}


}
