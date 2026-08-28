package org.example.Mapping.NewVersion.TwinStateMachine.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Transition;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.TwinActionBlockMapped;
import org.example.Mapping.TwinAction.TwinActionMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.StateDefinition;
import org.omg.sysml.lang.sysml.StateUsage;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.STATE)
@ToString(callSuper = true)
public class TwinStateMachineMapped<T extends TypeKind>
		extends TwinActionBlockMapped<Type, T>
		implements TwinStateMachine<T> {

	private CompartmentContainerMapped<TwinStateMachineMapped<Usage>> states =
			new CompartmentContainerMapped<>();

	private List<Transition> transitions = new ArrayList<>();

	private CompartmentMapped<TwinActionMapped<?, Usage>> entryAction;
	private CompartmentMapped<TwinActionMapped<?, Usage>> exitAction;
	private CompartmentMapped<TwinActionMapped<?, Usage>> doAction;

	public TwinStateMachineMapped(StateUsage sysmlElement) {
		super(sysmlElement);
	}

	public TwinStateMachineMapped(StateDefinition sysmlElement) {
		super(sysmlElement);
	}

	public static Class<TwinStateMachineMapped<Usage>> getRawUsageClass() {
		return (Class<TwinStateMachineMapped<Usage>>) (Class<?>) TwinStateMachineMapped.class;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		ActionUsage entry;
		ActionUsage exit;
		ActionUsage doActionUsage;
		List<? extends ActionUsage> actionsToScan;

		if (getSysmlElement() instanceof StateUsage su) {
			entry = su.getEntryAction();
			exit = su.getExitAction();
			doActionUsage = su.getDoAction();
			actionsToScan = su.getNestedAction();
		} else if (getSysmlElement() instanceof StateDefinition sd) {
			entry = sd.getEntryAction();
			exit = sd.getExitAction();
			doActionUsage = sd.getDoAction();
			actionsToScan = sd.getOwnedAction();
		} else {
			throw new MappingException(
					"TwinStateMachineMapped: sysmlElement is neither StateUsage nor StateDefinition."
			);
		}

		if (entry != null) {
			TwinActionMapped<?, Usage> mapped =
					context.map(
							entry,
							this,
							TwinActionMapped.getActionMappedUsageClass()
					);

			entryAction = new CompartmentMapped<>(mapped, false);
		}

		if (exit != null) {
			TwinActionMapped<?, Usage> mapped =
					context.map(
							exit,
							this,
							TwinActionMapped.getActionMappedUsageClass()
					);

			exitAction = new CompartmentMapped<>(mapped, false);
		}

		if (doActionUsage != null) {
			TwinActionMapped<?, Usage> mapped =
					context.map(
							doActionUsage,
							this,
							TwinActionMapped.getActionMappedUsageClass()
					);

			doAction = new CompartmentMapped<>(mapped, false);
		}

		List<TwinActionMapped<ActionUsage, Usage>> s = new ArrayList<>();

		for (ActionUsage action : actionsToScan) {
			if (action.equals(entry)
					|| action.equals(exit)
					|| action.equals(doActionUsage)) {
				continue;
			}

			s.add(
					context.map(
							action,
							this,
							rawClassOf(TwinActionMapped.class)
					)
			);
		}

		twinActionBlockUsages = s;

		transitions = twinActionBlockUsages.stream()
				.filter(Transition.class::isInstance)
				.map(Transition.class::cast)
				.toList();

		states = context.mapSlot(
				this,
				"states",
				getRawUsageClass()
		);
	}

	@Override
	public CompartmentContainerMapped<TwinStateMachineMapped<Usage>> getStates() {
		return states;
	}

	@Override
	public List<Transition> getTransitions() {
		return transitions;
	}

	@Override
	public CompartmentMapped<TwinActionMapped<?, Usage>> getEntryAction() {
		return entryAction;
	}

	@Override
	public CompartmentMapped<TwinActionMapped<?, Usage>> getExitAction() {
		return exitAction;
	}

	@Override
	public CompartmentMapped<TwinActionMapped<?, Usage>> getDoAction() {
		return doAction;
	}
}