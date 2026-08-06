package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.Succession;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.ReferenceUsage;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinSuccessionAction extends MappedElement<SuccessionAsUsage> implements Succession {
	private List<MappedReference<TwinActionBaseUsage<?>>> targets = new ArrayList<>();

	public TwinSuccessionAction(SuccessionAsUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {

		for (var member : getSysmlElement().getOwnedMember()) {
			if (!(member instanceof ReferenceUsage referenceUsage)) {
				continue;
			}

			var referent = referenceUsage.referencedFeatureTarget();

			if (!(referent instanceof ActionUsage actionUsage)) {
				continue;
			}

			targets.add(context.mapReference(actionUsage, TwinActionBaseUsage.getRawClass()));
		}
	}

	@Override
	public List<MappedReference<? extends Action>> getActionList() {
		return new ArrayList<>(targets);
	}
}