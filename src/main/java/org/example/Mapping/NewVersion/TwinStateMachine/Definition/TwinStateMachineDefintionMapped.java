package org.example.Mapping.NewVersion.TwinStateMachine.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Transition;
import org.example.Mapping.Interfaces.TwinStateMachine.Definition.TwinStateMachineDefinition;
import org.example.Mapping.Interfaces.TwinStateMachine.Usage.TwinStateMachineUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinStateMachine.Usage.TwinStateMachineUsageMapped;
import org.example.Mapping.TwinAction.TwinActionBlockMapped;
import org.example.Mapping.TwinAction.Usage.TwinActionBlockUsage;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.StateDefinition;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.STATE)
@ToString(callSuper = true)
public class TwinStateMachineDefintionMapped extends TwinActionBlockMapped<StateDefinition> implements TwinStateMachineDefinition {
	private List<TwinStateMachineUsageMapped> states = new ArrayList<>();
	private List<Transition> transitions = new ArrayList<>();
	private TwinActionUsageMapped<?> entryAction;
	private TwinActionUsageMapped<?> exitAction;
	private TwinActionUsageMapped<?> doAction;

	public TwinStateMachineDefintionMapped(StateDefinition sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		ActionUsage entry = getSysmlElement().getEntryAction();
		ActionUsage exit = getSysmlElement().getExitAction();
		ActionUsage doActionUsage = getSysmlElement().getDoAction();

		if (entry != null) {
			entryAction = context.map(entry, this, TwinActionBlockUsage.getRawClass());
		}

		if (exit != null) {
			exitAction = context.map(exit, this, TwinActionBlockUsage.getRawClass());
		}

		if (doActionUsage != null) {
			doAction = context.map(doActionUsage, this, TwinActionBlockUsage.getRawClass());
		}

		List<TwinActionUsageMapped<?>> s = new ArrayList<>();

		for (ActionUsage action : getSysmlElement().getOwnedAction()) {

			if (action.equals(entry) || action.equals(exit) || action.equals(doActionUsage)) {
				continue;
			}

			s.add(context.map(action, this, TwinActionBlockUsage.getRawClass()));
		}

		twinActionBlockUsages = s;
		transitions = twinActionBlockUsages.stream().filter(Transition.class::isInstance).map(Transition.class::cast).toList();
		states = context.mapSlot(this, "states", TwinStateMachineUsageMapped.class

		);
	}

	@Override
	public List<TwinStateMachineUsage> getStates() {
		return new ArrayList<>(states);
	}

	@Override
	public List<Transition> getTransitions() {
		return new ArrayList<>(transitions);
	}


	@Override
	public Action getEntryAction() {
		return entryAction;
	}

	@Override
	public Action getExitAction() {
		return exitAction;
	}

	@Override
	public Action getDoAction() {
		return doAction;
	}

}
