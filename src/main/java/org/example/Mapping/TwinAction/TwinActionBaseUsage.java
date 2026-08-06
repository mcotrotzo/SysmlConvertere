package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.Block;
import org.example.Mapping.Interfaces.Succession;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinActionBaseUsage<T extends ActionUsage> extends MappedElement<T> implements Block {
	List<TwinActionBaseUsage<?>> twinActionBaseUsages = new ArrayList<>();
	List<TwinSuccessionAction> twinSuccessionActions = new ArrayList<>();

	public TwinActionBaseUsage(T sysmlElement) {
		super(sysmlElement);
	}

	public static Class<TwinActionBaseUsage<?>> getRawClass() {
		return (Class<TwinActionBaseUsage<?>>) (Class<?>) TwinActionBaseUsage.class;

	}

	@Override
	public List<Action> getActions() {
		return new ArrayList<>(twinActionBaseUsages);
	}

	@Override
	public List<Succession> getSuccessions() {
		return new ArrayList<>(twinSuccessionActions);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void parse(MappingContext context) throws MappingException {
		twinActionBaseUsages = context.mapOwned(this, ActionUsage.class, getRawClass());
		twinSuccessionActions = context.mapOwned(this, SuccessionAsUsage.class, TwinSuccessionAction.class

		);

	}
}