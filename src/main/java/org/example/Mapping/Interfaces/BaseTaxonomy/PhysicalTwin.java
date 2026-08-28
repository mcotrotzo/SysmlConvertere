package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinPort.Actuators;
import org.example.Mapping.Interfaces.TwinPort.ConstPort;
import org.example.Mapping.Interfaces.TwinPort.Sensors;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;

import java.util.List;
import java.util.Optional;

public interface PhysicalTwin<T extends TypeKind> extends PhysicalTaxonomy<T> {

	CompartmentContainer<? extends Sensors<Usage>> getSensors();

	CompartmentContainer<? extends Actuators<Usage>> getActuators();

	CompartmentContainer<? extends TwinStateMachine<Usage>> getControlUnits();

	CompartmentContainer<? extends Flow<Usage>> getPhysicalFlows();

	CompartmentContainer<? extends ConstPort<Usage>> getConstPort();
}
