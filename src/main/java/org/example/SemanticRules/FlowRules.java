package org.example.SemanticRules;


import Executor.SemanticException;
import Executor.SemanticRule;
import Main.ResultConverter;
import Mapper.NewUtil;
import Model.Definition;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Flow.TwinFlowUsage;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyUsage;
import org.omg.sysml.lang.sysml.FeatureDirectionKind;

public class FlowRules extends SemanticRule {

	public FlowRules(NewUtil newUtil) {
		super(newUtil);
	}

	@Override
	public boolean isValid(ResultConverter resultConverter) throws SemanticException {
		for (TwinFlowUsage flow : resultConverter.getByType(TwinFlowUsage.class)) {
			if (flow.isLibrary()) {
				continue;
			}
			checkEnd(flow, flow.getSource(), flow.getSourceTaxonomy(), "source", FeatureDirectionKind.OUT);
			checkEnd(flow, flow.getTarget(), flow.getTargetTaxonomy(), "target", FeatureDirectionKind.IN);
			checkTypes(flow);
		}
		return true;
	}

	private void checkEnd(TwinFlowUsage flow, TwinAttributeUsage<?, ?> end, Class<? extends TaxonomyUsage> expected, String side, FeatureDirectionKind direction) throws SemanticException {
		TaxonomyUsage<?, ?> taxonomy = end.getTaxonomy().orElseThrow(() -> new SemanticException(
				"Flow '%s' %s '%s' has no taxonomy.".formatted(flow.getName(), side, end.getName())));
		if (!expected.isInstance(taxonomy)) {
			throw new SemanticException("Flow '%s' %s '%s' belongs to taxonomy '%s', expected '%s'."
					.formatted(flow.getName(), side, end.getName(), taxonomy.getClass().getSimpleName(), expected.getSimpleName()));
		}
		FeatureDirectionKind actual = end.getDirection().orElseThrow(() -> new SemanticException(
				"Flow '%s' %s '%s' has no direction.".formatted(flow.getName(), side, end.getName())));
		if (actual != direction && actual != FeatureDirectionKind.INOUT) {
			throw new SemanticException("Flow '%s' %s '%s' must have direction %s or INOUT, but got '%s'."
					.formatted(flow.getName(), side, end.getName(), direction, actual));
		}
	}

	private void checkTypes(TwinFlowUsage flow) throws SemanticException {
		Definition<?, ?> source = flow.getSource().getDefinition().orElse(null);
		Definition<?, ?> target = flow.getTarget().getDefinition().orElse(null);
		if (source == null || target == null) {
			return;
		}
		if (!conforms(source, target)) {
			throw new SemanticException("Flow '%s' has incompatible endpoint types: source '%s' has type '%s', target '%s' expects '%s'."
					.formatted(flow.getName(), flow.getSource().getName(), source.getName(), flow.getTarget().getName(), target.getName()));
		}
	}

	private boolean conforms(Definition<?, ?> source, Definition<?, ?> target) {
		if (source.getId().equals(target.getId())) {
			return true;
		}
		for (Definition<?, ?> general : source.getSuperDefinitions()) {
			if (conforms(general, target)) {
				return true;
			}
		}
		return false;
	}
}

