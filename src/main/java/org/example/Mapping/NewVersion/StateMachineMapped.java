package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.TwinActionBaseUsage;
import org.example.Mapping.TwinAction.TwinSuccessionAction;
import org.example.Mapping.TwinAction.TwinTransitionUsageMapped;
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
	private List<TwinAttributeMapped> localAttributes = new ArrayList<>();
	private List<StateMachineMapped> states = new ArrayList<>();

	private TwinActionBaseUsage<?> entryAction;
	private TwinActionBaseUsage<?> exitAction;
	private TwinActionBaseUsage<?> doAction;

	private List<Transition> transitions = new ArrayList<>();

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

		ActionUsage entry = getSysmlElement().getEntryAction();
		ActionUsage exit = getSysmlElement().getExitAction();
		ActionUsage doActionUsage = getSysmlElement().getDoAction();

		if (entry != null) {
			entryAction = context.map(
					entry,
					this,
					TwinActionBaseUsage.getRawClass()
			);
		}

		if (exit != null) {
			exitAction = context.map(
					exit,
					this,
					TwinActionBaseUsage.getRawClass()
			);
		}

		if (doActionUsage != null) {
			doAction = context.map(
					doActionUsage,
					this,
					TwinActionBaseUsage.getRawClass()
			);
		}

		List<TwinActionBaseUsage<?>> s = new ArrayList<>();

		for (ActionUsage action : getSysmlElement().getNestedAction()) {

			if (action.equals(entry)
					|| action.equals(exit)
					|| action.equals(doActionUsage)) {
				continue;
			}

			s.add(
					context.map(
							action,
							this,
							TwinActionBaseUsage.getRawClass()
					)
			);
		}

		twinActionBaseUsages = s;
		transitions = twinActionBaseUsages.stream()
				.filter(Transition.class::isInstance)
				.map(Transition.class::cast)
				.toList();

		localAttributes =
				context.mapSlot(
						this,
						"local_Attributes",
						TwinAttributeMapped.class

		);

		states =
				context.mapSlot(
						this,
						"states",
						StateMachineMapped.class

		);
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

	@Override
	public List<Succession> getSuccession() {
		return getSuccessions();
	}
}

