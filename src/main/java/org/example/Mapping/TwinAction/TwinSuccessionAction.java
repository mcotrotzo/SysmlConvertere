package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.Succession;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Feature;
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

		var sourceFeature = getSysmlElement().getSourceFeature();
		var targetFeature = getSysmlElement().getTargetFeature();

		if (sourceFeature == null) {
			throw new MappingException(
					"Succession has no source feature"
			);
		}

		if (targetFeature == null) {
			throw new MappingException(
					"Succession has no target feature"
			);
		}


		if (!(sourceFeature instanceof ActionUsage sourceAction)) {
			throw new MappingException(
					"Succession source is not an ActionUsage"
			);
		}
		targets.add(
				context.mapReference(
						sourceAction,
						TwinActionBaseUsage.getRawClass()
				)
		);

		for(Feature target:targetFeature){
			if (!(target instanceof ActionUsage targetAction)) {
				throw new MappingException(
						"Succession target is not an ActionUsage"
				);
			}
			targets.add(
					context.mapReference(
							targetAction,
							TwinActionBaseUsage.getRawClass()
					)
			);
		}
	}

	@Override
	public List<Reference<? extends Action>> getActionList() {
		return new ArrayList<>(targets);
	}
}