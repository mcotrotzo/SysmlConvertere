package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwin;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
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

@MappedElementType(LibraryNameSpaces.PHYSICAL_TWIN)
@ToString(callSuper = true)
public class PhysicalTwinMapped<T extends TypeKind>
		extends PhysicalTwinTaxonomyMapped<T>
		implements PhysicalTwin<T> {

	private CompartmentContainerMapped<SensorMapped<Usage>> sensors;

	private CompartmentContainerMapped<ActuatorMapped<Usage>> actuators;

	private CompartmentContainerMapped<TwinStateMachineMapped<Usage>> controlUnits;

	private CompartmentContainerMapped<ConstPortMapped<Usage>> constAttributes;

	private CompartmentContainerMapped<PhysicalFlowMapped<Usage>> physicalFlows;


	public PhysicalTwinMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		sensors = context.mapSlot(
				this,
				"sensors",
				rawClassOf(SensorMapped.class)
		);

		actuators = context.mapSlot(
				this,
				"actuators",
				rawClassOf(ActuatorMapped.class)
		);

		controlUnits = context.mapSlot(
				this,
				"controlUnit",
				rawClassOf(TwinStateMachineMapped.class)
		);

		constAttributes = context.mapSlot(
				this,
				"constPort",
				rawClassOf(ConstPortMapped.class)
		);

		physicalFlows = context.mapSlot(
				this,
				"physicalFlows",
				rawClassOf(PhysicalFlowMapped.class)
		);
	}

	@Override
	public CompartmentContainerMapped<SensorMapped<Usage>> getSensors() {
		return sensors;
	}

	@Override
	public CompartmentContainerMapped<ActuatorMapped<Usage>> getActuators() {
		return actuators;
	}

	@Override
	public CompartmentContainerMapped<TwinStateMachineMapped<Usage>> getControlUnits() {
		return controlUnits;
	}

	@Override
	public CompartmentContainerMapped<PhysicalFlowMapped<Usage>> getPhysicalFlows() {
		return physicalFlows;
	}

	@Override
	public CompartmentContainerMapped<ConstPortMapped<Usage>> getConstPort() {
		return constAttributes;
	}
}