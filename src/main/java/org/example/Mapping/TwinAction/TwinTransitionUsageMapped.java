package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.Transition;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.TransitionUsage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinTransitionUsageMapped extends TwinActionBaseUsage<TransitionUsage> implements Transition {
	private MappedReference<TwinActionBaseUsage<?>> source;
	private MappedReference<TwinActionBaseUsage<?>> target;
	private List<TwinExpression<?>> guard = new ArrayList<>();
	private TwinActionBaseUsage<?> effectAction;

	public TwinTransitionUsageMapped(TransitionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public MappedReference<TwinActionBaseUsage<?>> getSource() {
		return source;
	}

	@Override
	public MappedReference<TwinActionBaseUsage<?>> getTarget() {
		return target;
	}

	@Override
	public List<Expression> getGuard() {
		return new ArrayList<>(guard);
	}

	@Override
	public Action getEffectAction() {
		return (Action) effectAction;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		source = context.mapReference(getSysmlElement().getSource(), TwinActionBaseUsage.getRawClass());
		target = context.mapReference(getSysmlElement().getTarget(), TwinActionBaseUsage.getRawClass());
		guard = this.getSysmlElement().getGuardExpression().stream().map(e -> {
			try {
				return context.map(e, this, TwinExpression.class);
			} catch (MappingException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		effectAction = this.getSysmlElement().getEffectAction().stream().map(x -> {
			try {
				return context.map(x, this, TwinActionBaseUsage.class);
			} catch (MappingException ex) {
				throw new RuntimeException(ex);
			}
		}).findFirst().orElse(null);
	}
}
