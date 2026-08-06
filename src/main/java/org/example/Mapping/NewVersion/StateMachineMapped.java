package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.StateMachine;
import org.example.Mapping.Interfaces.Transition;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.TwinActionBaseUsage;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.StateUsage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.STATE)
@ToString(callSuper = true)
public class StateMachineMapped extends TwinActionBaseUsage<StateUsage> implements StateMachine {


	private Set<TwinAttributeMapped> localAttributes = new HashSet<>();
	private Set<StateMachineMapped> states = new HashSet<>();
	private Set<TwinActionBaseUsage> actions = new HashSet<>();

	private TwinActionBaseUsage<?> entryAction;
	private TwinActionBaseUsage<?> exitAction;
	private TwinActionBaseUsage<?> doAction;

	public StateMachineMapped(StateUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute> getLocalAttributes() {
		return new ArrayList<>(localAttributes);
	}

	@Override
	public List<StateMachine> getStates() {
		return new ArrayList<>(states);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		ActionUsage entry = this.getSysmlElement().getEntryAction();
		ActionUsage exit = this.getSysmlElement().getExitAction();
		ActionUsage doA = this.getSysmlElement().getDoAction();

		if (entry != null) {
			entryAction = context.map(entry, this, TwinActionBaseUsage.class);
		}
		if (exit != null) {
			exitAction = context.map(exit, this, TwinActionBaseUsage.class);
		}

		if (doA != null) {
			doAction = context.map(doA, this, TwinActionBaseUsage.class);
		}


		actions = this.getSysmlElement().getNestedAction().stream().filter(x -> !(x.equals(entry) || x.equals(exit) || x.equals(doA))).map(x -> {
			try {
				return context.map(x, this, TwinActionBaseUsage.class);
			} catch (MappingException e) {
				throw new RuntimeException(e);
			}
		}).collect(java.util.stream.Collectors.toSet());

		localAttributes = new HashSet<>(context.mapSlot(this, "local_Attributes", TwinAttributeMapped.class));

		states = new HashSet<>(context.mapSlot(this, "states", StateMachineMapped.class));

	}

	@Override
	public List<Transition> getTransitions() {
		return actions.stream().filter(Transition.class::isInstance).map(Transition.class::cast).toList();
	}

	@Override
	public Action getEntryAction() {
		return (Action) entryAction;
	}

	@Override
	public Action getExitAction() {
		return (Action) exitAction;
	}

	@Override
	public Action getDoAction() {
		return (Action) doAction;
	}
}

