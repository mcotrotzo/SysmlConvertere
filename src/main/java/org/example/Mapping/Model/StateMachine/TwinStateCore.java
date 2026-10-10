package org.example.Mapping.Model.StateMachine;


import Model.Predefined.MetaClasses.Action.ActionMapUsage;
import Mapper.Mapper;
import Model.AbstractType;
import Model.Slots;
import org.omg.sysml.lang.sysml.TransitionUsage;
import lombok.Getter;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.TwinTransitionUsage;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.StateDefinition;
import org.omg.sysml.lang.sysml.StateUsage;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TwinStateCore extends ActionBlockCore {

	@Getter private List<TwinStateUsage> states = List.of();
	@Getter private List<TwinTransitionUsage> transitions = List.of();
	@Getter private Optional<ActionMapUsage<?, ?, ?>> entryAction = Optional.empty();
	@Getter private Optional<ActionMapUsage<?, ?, ?>> doAction = Optional.empty();
	@Getter private Optional<ActionMapUsage<?, ?, ?>> exitAction = Optional.empty();

	public TwinStateCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
		super.fillSlots(owner);

		ActionUsage entry;
		ActionUsage doUsage;
		ActionUsage exit;
		if (sysmlElement instanceof StateUsage state) {
			entry = state.getEntryAction();
			doUsage = state.getDoAction();
			exit = state.getExitAction();
		} else if (sysmlElement instanceof StateDefinition state) {
			entry = state.getEntryAction();
			doUsage = state.getDoAction();
			exit = state.getExitAction();
		} else {
			throw new IllegalStateException("'%s' is neither StateUsage nor StateDefinition.".formatted(owner.getName()));
		}

		Class<ActionMapUsage<?, ?, ?>> actionClass = Slots.rawClassOf(ActionMapUsage.class);
		entryAction = Slots.mapAll(mapper, owner, Stream.ofNullable(entry).toList(), actionClass).stream().findFirst();
		doAction = Slots.mapAll(mapper, owner, Stream.ofNullable(doUsage).toList(), actionClass).stream().findFirst();
		exitAction = Slots.mapAll(mapper, owner, Stream.ofNullable(exit).toList(), actionClass).stream().findFirst();

		states = mapper.mapSlot("states", owner, TwinStateUsage.class);
		transitions = mapper.mapOwnedElement(TransitionUsage.class, owner, TwinTransitionUsage.class);
	}

	/** Nested actions without entry, do and exit action (as in the old mapper). */
	@Override
	public List<ActionMapUsage<?, ?, ?>> getActions() {
		List<ActionMapUsage<?, ?, ?>> nested = new ArrayList<>(super.getActions());
		nested.removeIf(a -> entryAction.orElse(null) == a || doAction.orElse(null) == a || exitAction.orElse(null) == a);
		return nested;
	}
}
