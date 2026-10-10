package org.example.Mapping.Model.StateMachine;


import Mapper.Mapper;
import Model.AbstractType;
import org.example.Mapping.Model.Action.TriggerCore;
import org.example.Mapping.Model.Action.TwinTriggerUsage;
import org.omg.sysml.lang.sysml.Type;

import java.util.Optional;

public class TwinStateMachineCore extends TwinStateCore implements TriggerCore {
	private TwinTriggerUsage trigger;

	public TwinStateMachineCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
		super.fillSlots(owner);
		trigger = mapper.mapSingleSlot("trigger", owner, TwinTriggerUsage.class);
	}

	@Override
	public Optional<TwinTriggerUsage> getTrigger() {
		return Optional.ofNullable(trigger);
	}
}
