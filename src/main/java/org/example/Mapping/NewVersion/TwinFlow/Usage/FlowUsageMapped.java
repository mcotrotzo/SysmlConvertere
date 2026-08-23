package org.example.Mapping.NewVersion.TwinFlow.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.Reference;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
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
        var sourceFeature = FeatureUtil.getBasicFeatureOf(getSysmlElement().getSourceOutputFeature());
        var targetFeature = FeatureUtil.getBasicFeatureOf(getSysmlElement().getTargetInputFeature());

        if (sourceFeature == null || targetFeature == null) {
            throw new MappingException(
                    "Flow '%s' must have a source output feature and a target input feature."
                            .formatted(getName())
            );
        }

        source = context.mapReference(sourceFeature, TwinAttributeUsageMapped.class);
        target = context.mapReference(targetFeature, TwinAttributeUsageMapped.class);
    }

    @Override
    public void postValidate() throws MappingException {
        super.postValidate();

        TwinAttributeUsageMapped sourceAttribute = source.getReferent();
        TwinAttributeUsageMapped targetAttribute = target.getReferent();

        if (!sourceContexts().contains(sourceAttribute.getContext())) {
            throw new MappingException(
                    "Flow '%s' has source context '%s', allowed contexts: %s."
                            .formatted(getName(), sourceAttribute.getContext(), sourceContexts())
            );
        }

        if (!targetContexts().contains(targetAttribute.getContext())) {
            throw new MappingException(
                    "Flow '%s' has target context '%s', allowed contexts: %s."
                            .formatted(getName(), targetAttribute.getContext(), targetContexts())
            );
        }

        Direction sourceDirection = sourceAttribute.getDirection();
        if (sourceDirection != Direction.OUT && sourceDirection != Direction.INOUT) {
            throw new MappingException(
                    "Flow '%s' source '%s' must have direction OUT or INOUT, but got '%s'."
                            .formatted(getName(), sourceAttribute.getName(), sourceDirection)
            );
        }

        Direction targetDirection = targetAttribute.getDirection();
        if (targetDirection != Direction.IN && targetDirection != Direction.INOUT) {
            throw new MappingException(
                    "Flow '%s' target '%s' must have direction IN or INOUT, but got '%s'."
                            .formatted(getName(), targetAttribute.getName(), targetDirection)
            );
        }
    }

    protected abstract Set<Context> sourceContexts();

    protected abstract Set<Context> targetContexts();
}
