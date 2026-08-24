package org.example.Mapping.NewVersion.TwinFlow.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.util.FeatureUtil;

import java.util.Set;

@ToString(callSuper = true)
public abstract class FlowUsageMapped
		extends TwinActionUsageMapped<org.omg.sysml.lang.sysml.FlowUsage>
		implements org.example.Mapping.Interfaces.TwinFlow.FlowUsage {

	private MappedReference<TwinAttributeUsageMapped> source;
	private MappedReference<TwinAttributeUsageMapped> target;

	public FlowUsageMapped(org.omg.sysml.lang.sysml.FlowUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public Reference<? extends TwinAttributeUsage> getSource() {
		return source;
	}

	@Override
	public Reference<? extends TwinAttributeUsage> getTarget() {
		return target;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		Feature sourceFeature = resolveFlowEndpoint(
				getSysmlElement().getSourceOutputFeature()
		);

		Feature targetFeature = resolveFlowEndpoint(
				getSysmlElement().getTargetInputFeature()
		);

		source = context.mapReference(
				sourceFeature,
				TwinAttributeUsageMapped.class
		);

		target = context.mapReference(
				targetFeature,
				TwinAttributeUsageMapped.class
		);
	}

	private static Feature resolveFlowEndpoint(Feature feature)
			throws MappingException {

		if (feature == null) {
			throw new MappingException(
					"Flow endpoint must not be null."
			);
		}

		Feature basic = FeatureUtil.getBasicFeatureOf(feature);

		if (basic == null) {
			throw new MappingException(
					"Could not resolve basic feature for flow endpoint '%s'."
							.formatted(feature.getName())
			);
		}

		var redefinitions = basic.getOwnedRedefinition();

		if (redefinitions.size() != 1) {
			throw new MappingException(
					"Flow endpoint '%s' must redefine exactly one feature, but redefines %d."
							.formatted(
									basic.getName(),
									redefinitions.size()
							)
			);
		}

		Feature referencedFeature =
				redefinitions.get(0).getRedefinedFeature();

		if (referencedFeature == null) {
			throw new MappingException(
					"Flow endpoint '%s' has no redefined feature."
							.formatted(basic.getName())
			);
		}

		return referencedFeature;
	}

	@Override
	public void postValidate() throws MappingException {
		super.postValidate();

		TwinAttributeUsageMapped sourceAttribute = source.getReferent();
		TwinAttributeUsageMapped targetAttribute = target.getReferent();

		if (!sourceContexts().contains(sourceAttribute.getContext())) {
			throw new MappingException(
					("Flow '%s' has source context '%s', allowed contexts: %s.")
							.formatted(getSysmlElement().path(),
									sourceAttribute.getContext(),
									sourceContexts()
							)
			);
		}

		if (!targetContexts().contains(targetAttribute.getContext())) {
			throw new MappingException(
					"Flow '%s' has target context '%s', allowed contexts: %s."
							.formatted(
									getName(),
									targetAttribute.getContext(),
									targetContexts()
							)
			);
		}

		Direction sourceDirection = sourceAttribute.getDirection();

		if (sourceDirection != Direction.OUT
				&& sourceDirection != Direction.INOUT) {

			throw new MappingException(
					"Flow '%s' source '%s' must have direction OUT or INOUT, but got '%s'."
							.formatted(
									getName(),
									sourceAttribute.getName(),
									sourceDirection
							)
			);
		}

		Direction targetDirection = targetAttribute.getDirection();

		if (targetDirection != Direction.IN
				&& targetDirection != Direction.INOUT) {

			throw new MappingException(
					"Flow '%s' target '%s' must have direction IN or INOUT, but got '%s'."
							.formatted(
									getName(),
									targetAttribute.getName(),
									targetDirection
							)
			);
		}
	}

	protected abstract Set<Context> sourceContexts();

	protected abstract Set<Context> targetContexts();
}