package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Transition;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.TransitionUsage;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinTransitionUsageMapped extends TwinActionUsageMapped<TransitionUsage> implements Transition {
	private MappedReference<TwinActionUsageMapped<?>> source;
	private MappedReference<TwinActionUsageMapped<?>> target;
	private List<TwinExpression<?>> guard = new ArrayList<>();
	private TwinActionUsageMapped<?> effectAction;

	public TwinTransitionUsageMapped(TransitionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public MappedReference<TwinActionUsageMapped<?>> getSource() {
		return source;
	}

	@Override
	public MappedReference<TwinActionUsageMapped<?>> getTarget() {
		return target;
	}

	@Override
	public List<org.example.Mapping.Interfaces.TwinExpression.TwinExpression> getGuard() {
		return new ArrayList<>(guard);
	}

	@Override
	public Action getEffectAction() {
		return (Action) effectAction;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		source = context.mapReference(getSysmlElement().getSource(), getRawUsage());
		target = context.mapReference(getSysmlElement().getTarget(), getRawUsage());
		guard = this.getSysmlElement().getGuardExpression().stream().map(e -> {
			try {
				return context.map(e, this, TwinExpression.class);
			} catch (MappingException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		effectAction = this.getSysmlElement().getEffectAction().stream().map(x -> {
			try {
				return context.map(x, this, TwinActionUsageMapped.class);
			} catch (MappingException ex) {
				throw new RuntimeException(ex);
			}
		}).findFirst().orElse(null);
	}

	private Class<TwinActionUsageMapped<?>>  getRawUsage(){
		return (Class<TwinActionUsageMapped<?>>) (Class<?>) TwinActionUsageMapped.class;
	}
}
