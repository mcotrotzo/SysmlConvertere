package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.lang.sysml.FeatureChaining;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinFeatureChainExpression extends TwinExpression<FeatureChainExpression> implements FeatureReference {

	private final List<MappedElement<?, Usage>> chain = new ArrayList<>();

	private MappedReference<? extends Compartment<? extends MappedElement<?, Usage>>> target;

	public TwinFeatureChainExpression(FeatureChainExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		for (var argument : getSysmlElement().getArgument()) {
			if (argument instanceof FeatureReferenceExpression reference) {
				chain.add(context.map(reference.getReferent(), null, rawClassOf(MappedElement.class)));
			}
		}

		var targetFeature = getSysmlElement().getTargetFeature();
		boolean chainedTarget = false;

		for (var relationship : targetFeature.getOwnedRelationship()) {
			if (relationship instanceof FeatureChaining chaining) {
				chainedTarget = true;

				for (var element : chaining.getTarget()) {
					if (element instanceof Feature feature) {
						chain.add(context.map(feature, null, rawClassOf(MappedElement.class)));
					}
				}
			}
		}

		if (!chainedTarget) {
			chain.add(context.map(targetFeature, null, rawClassOf(MappedElement.class)));
		}
	}


	@Override
	public void postParse(MappingContext context) {
		super.postParse(context);

		target = new MappedReference<>(context.resolveCompartmentChain(chain));
	}


	@Override
	public List<? extends Reference<? extends Type<Usage>>> getChain() {
		return chain.stream()
				.map(MappedReference::new)
				.toList();
	}

	@Override
	public Reference<? extends Compartment<? extends Type<Usage>>> getAsCompartment() {
		return target;
	}
}