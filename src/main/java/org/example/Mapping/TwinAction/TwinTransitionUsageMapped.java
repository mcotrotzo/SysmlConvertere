package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Transition;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.TransitionUsage;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinTransitionUsageMapped extends TwinActionMapped<TransitionUsage,Usage> implements Transition {
	private MappedReference<TwinActionMapped<ActionUsage,Usage>> source;
	private MappedReference<TwinActionMapped<ActionUsage,Usage>> target;
	private List<TwinExpression<?>> guard = new ArrayList<>();
	private TwinActionMapped<ActionUsage,Usage> effectAction;

	public TwinTransitionUsageMapped(TransitionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public MappedReference<TwinActionMapped<ActionUsage,Usage>> getSource() {
		return source;
	}

	@Override
	public MappedReference<TwinActionMapped<ActionUsage,Usage>> getTarget() {
		return target;
	}

	@Override
	public List<org.example.Mapping.Interfaces.TwinExpression.TwinExpression> getGuard() {
		return new ArrayList<>(guard);
	}

	@Override
	public Action<Usage> getEffectAction() {
		return effectAction;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {


		source = context.mapReference(getSysmlElement().getSource(), rawClassOf(TwinActionMapped.class));
		target = context.mapReference(getSysmlElement().getTarget(), rawClassOf(TwinActionMapped.class));
		guard = this.getSysmlElement().getGuardExpression().stream().map(e -> {
			try {
				return context.map(e, this, TwinExpression.class);
			} catch (MappingException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		Class<TwinActionMapped<ActionUsage,Usage>> twinActionMappedClass = rawClassOf(TwinActionMapped.class);
		effectAction = this.getSysmlElement().getEffectAction().stream().map(x -> {
			try {
				return context.map(x, this, twinActionMappedClass);
			} catch (MappingException ex) {
				throw new RuntimeException(ex);
			}
		}).findFirst().orElse(null);
	}

}
