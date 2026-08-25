package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinPort.Actuators;
import org.example.Mapping.Interfaces.TwinPort.ConstPort;
import org.example.Mapping.Interfaces.TwinPort.Sensors;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;

import java.util.List;
import java.util.Optional;

public interface PhysicalTwin <T extends TypeKind> extends Type<T> {

	List<Sensors<Usage>> getSensors();

	List<Actuators<Usage>> getActuators();

	List<TwinStateMachine<Usage>> getControlUnits();
	List<Flow<Usage>> getPhysicalFlows();

	Optional<ConstPort<Usage>> getConstPort();


	@Override
	default Context getContext() {
		return Context.PHYSICAL;
	}
}
