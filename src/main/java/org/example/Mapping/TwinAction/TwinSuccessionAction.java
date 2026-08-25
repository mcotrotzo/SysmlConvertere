package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAction.Succession;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinSuccessionAction extends MappedElement<SuccessionAsUsage, Usage> implements Succession {
	private List<MappedReference<TwinActionMapped<?,Usage>>> targets = new ArrayList<>();

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
						TwinForLoopActionMapped.getActionMappedUsageClass()
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
							TwinForLoopActionMapped.getActionMappedUsageClass()
					)
			);
		}
	}
	@Override
	public List<Reference<? extends Action<Usage>>> getActionList() {
		return new ArrayList<>(targets);
	}
}