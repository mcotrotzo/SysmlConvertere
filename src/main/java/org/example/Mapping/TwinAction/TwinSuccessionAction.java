package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAction.Succession;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAction.Usage.TwinActionBlockUsage;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinSuccessionAction extends MappedElement<SuccessionAsUsage> implements Succession {
	private List<MappedReference<TwinActionUsageMapped<?>>> targets = new ArrayList<>();

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
						getRawUsage()
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
							getRawUsage()
					)
			);
		}
	}

	private Class<TwinActionUsageMapped<?>>  getRawUsage(){
		return (Class<TwinActionUsageMapped<?>>) (Class<?>) TwinActionUsageMapped.class;
	}

	@Override
	public List<Reference<? extends Action>> getActionList() {
		return new ArrayList<>(targets);
	}
}