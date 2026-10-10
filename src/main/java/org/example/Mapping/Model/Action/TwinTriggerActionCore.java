package org.example.Mapping.Model.Action;


import Mapper.Mapper;
import Model.AbstractType;
import org.omg.sysml.lang.sysml.Type;

import java.util.Optional;


public class TwinTriggerActionCore extends ActionBlockCore implements TriggerCore {
	private TwinTriggerUsage trigger;

	public TwinTriggerActionCore(Type sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots(AbstractType<?, ?> owner) {
		super.fillSlots(owner);
		trigger = mapper.mapSingleSlot("trigger", owner, TwinTriggerUsage.class);
	}

	@Override
	public Optional<TwinTriggerUsage> getTrigger() {
		return Optional.ofNullable(trigger);
	}
}
