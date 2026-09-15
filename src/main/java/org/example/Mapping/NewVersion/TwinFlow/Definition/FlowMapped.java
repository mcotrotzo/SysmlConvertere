package org.example.Mapping.NewVersion.TwinFlow.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.TaxonomyMapped;
import org.example.Mapping.TwinAction.TwinActionMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.util.FeatureUtil;

import java.lang.Class;
import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.TWIN_FLOW)
@ToString(callSuper = true)
public class FlowMapped<T extends TypeKind> extends TwinActionMapped<Type, T> implements Flow<T> {

	private CompartmentMapped<TaxonomyMapped<Usage>> taxonomySource;
	private CompartmentMapped<TaxonomyMapped<Usage>> taxonomyTarget;

	private MappedReference<? extends CompartmentMapped<? extends TwinAttributeMapped<Usage>>> source;
	private MappedReference<? extends CompartmentMapped<? extends TwinAttributeMapped<Usage>>> target;

	public FlowMapped(FlowDefinition sysmlElement) {
		super(sysmlElement);
	}

	public FlowMapped(FlowUsage sysmlElement) {
		super(sysmlElement);
	}

	private static Feature resolveFlowEndpoint(Feature feature) throws MappingException {
		if (feature == null) {
			throw new MappingException("Flow endpoint must not be null.");
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
							.formatted(basic.getName(), redefinitions.size())
			);
		}

		Feature referencedFeature =
				redefinitions.getFirst().getRedefinedFeature();

		if (referencedFeature == null) {
			throw new MappingException(
					"Flow endpoint '%s' has no redefined feature."
							.formatted(basic.getName())
			);
		}

		return referencedFeature;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		if (!(getSysmlElement() instanceof FlowUsage flowUsage)) {
			return;
		}

		if (context.getUtils().isFromDTLibrary(flowUsage)) {
			return;
		}

		Class<TaxonomyMapped<Usage>> taxonomyClass =
				rawClassOf(TaxonomyMapped.class);

		taxonomySource = context
				.mapPrivateSlot(this, "tax1", taxonomyClass)
				.getCompartment()
				.stream()
				.findFirst()
				.orElseThrow(() ->
						new MappingException(
								"Flow '%s' has no source taxonomy."
										.formatted(getName())
						)
				);

		taxonomyTarget = context
				.mapPrivateSlot(this, "tax2", taxonomyClass)
				.getCompartment()
				.stream()
				.findFirst()
				.orElseThrow(() ->
						new MappingException(
								"Flow '%s' has no target taxonomy."
										.formatted(getName())
						)
				);

		Feature sourceFeature =
				resolveFlowEndpoint(flowUsage.getSourceOutputFeature());

		Feature targetFeature =
				resolveFlowEndpoint(flowUsage.getTargetInputFeature());

		List<Feature> sourceChain =
				getEndpointChain(flowUsage, "source");

		List<Feature> targetChain =
				getEndpointChain(flowUsage, "target");

		if (sourceChain.isEmpty() || targetChain.isEmpty()) {
			if (!(flowUsage instanceof ConnectorAsUsage connector)) {
				throw new MappingException(
						"Flow '%s' has no feature chaining and is not a ConnectorAsUsage."
								.formatted(flowUsage.path())
				);
			}

			List<Feature> relatedFeatures =
					connector.getRelatedFeature();

			if (relatedFeatures.size() != 2) {
				throw new MappingException(
						"Flow '%s' has no feature chaining and must have exactly two related features, but has %d."
								.formatted(
										flowUsage.path(),
										relatedFeatures.size()
								)
				);
			}

			if (sourceChain.isEmpty()) {
				sourceChain = new ArrayList<>();
				sourceChain.add(relatedFeatures.get(0));
			}

			if (targetChain.isEmpty()) {
				targetChain = new ArrayList<>();
				targetChain.add(relatedFeatures.get(1));
			}
		}

		source = mapEndpoint(
				context,
				sourceChain,
				sourceFeature
		);

		target = mapEndpoint(
				context,
				targetChain,
				targetFeature
		);
	}

	private static MappedReference<? extends CompartmentMapped<TwinAttributeMapped<Usage>>> mapEndpoint(
			MappingContext context,
			List<Feature> chain,
			Feature endpoint
	) throws MappingException {

		if (chain.isEmpty()) {
			throw new MappingException(
					"Cannot resolve flow endpoint '%s' without a chain."
							.formatted(endpoint.getName())
			);
		}

		List<MappedElement<?, Usage>> mappedChain = new ArrayList<>();

		for (Feature feature : chain) {
			mappedChain.add(
					context.map(
							feature,
							null,
							rawClassOf(MappedElement.class)
					)
			);
		}

		mappedChain.add(
				context.map(
						endpoint,
						null,
						rawClassOf(MappedElement.class)
				)
		);

		CompartmentMapped<? extends MappedElement<?, Usage>> resolved =
				context.resolveCompartmentChain(mappedChain);

		if (!(resolved.getElement() instanceof TwinAttributeMapped<?>)) {
			throw new MappingException(
					"Resolved compartment for endpoint '%s' does not contain a TwinAttributeMapped."
							.formatted(endpoint.getName())
			);
		}

		@SuppressWarnings("unchecked")
		CompartmentMapped<TwinAttributeMapped<Usage>> compartment =
				(CompartmentMapped<TwinAttributeMapped<Usage>>) (CompartmentMapped<?>) resolved;

		return new MappedReference<>(compartment);
	}

	private static List<Feature> getEndpointChain(
			FlowUsage flowUsage,
			String endpointName
	) throws MappingException {

		for (var membership : flowUsage.getOwnedFeatureMembership()) {
			if (!(membership instanceof EndFeatureMembership endMembership)) {
				continue;
			}

			Feature end =
					endMembership.getOwnedMemberFeature();

			if (end == null || !endpointName.equals(end.getName())) {
				continue;
			}

			List<Feature> chain =
					new ArrayList<>();

			for (var it = end.eAllContents(); it.hasNext(); ) {
				var object = it.next();

				if (!(object instanceof FeatureChaining chaining)) {
					continue;
				}

				Feature chainedFeature =
						chaining.getChainingFeature();

				if (chainedFeature == null) {
					throw new MappingException(
							"Flow '%s' endpoint '%s' contains a feature chaining without a chaining feature."
									.formatted(
											flowUsage.path(),
											endpointName
									)
					);
				}

				chain.add(chainedFeature);
			}

			return chain;
		}

		throw new MappingException(
				"Flow '%s' has no '%s' endpoint."
						.formatted(
								flowUsage.path(),
								endpointName
						)
		);
	}

	@Override
	public CompartmentMapped<TaxonomyMapped<Usage>> sourceContexts() {
		return taxonomySource;
	}

	@Override
	public CompartmentMapped<TaxonomyMapped<Usage>> targetContexts() {
		return taxonomyTarget;
	}

	@Override
	public Reference<? extends Compartment<? extends TwinAttributeMapped<Usage>>> getSource() {
		return source;
	}

	@Override
	public Reference<? extends Compartment<? extends TwinAttributeMapped<Usage>>> getTarget() {
		return target;
	}

	@Override
	public void postValidate() throws MappingException {
		super.postValidate();

		if (!(getSysmlElement() instanceof FlowUsage)) {
			return;
		}

		if (source == null || target == null) {
			return;
		}

		if (taxonomySource == null || taxonomyTarget == null) {
			return;
		}

		if (!(source.getReferent().getElement() instanceof TwinAttributeMapped<?> sourceRaw)) {
			throw new MappingException(
					"Flow '%s' source compartment does not contain a TwinAttributeMapped."
							.formatted(getSysmlElement().path())
			);
		}

		if (!(target.getReferent().getElement() instanceof TwinAttributeMapped<?> targetRaw)) {
			throw new MappingException(
					"Flow '%s' target compartment does not contain a TwinAttributeMapped."
							.formatted(getSysmlElement().path())
			);
		}

		@SuppressWarnings("unchecked")
		TwinAttributeMapped<Usage> sourceAttribute =
				(TwinAttributeMapped<Usage>) sourceRaw;

		@SuppressWarnings("unchecked")
		TwinAttributeMapped<Usage> targetAttribute =
				(TwinAttributeMapped<Usage>) targetRaw;

		validateEndpointTypes(sourceAttribute, targetAttribute);

		validateTaxonomy(
				sourceAttribute,
				taxonomySource.getElement(),
				"source"
		);

		validateTaxonomy(
				targetAttribute,
				taxonomyTarget.getElement(),
				"target"
		);

		Direction sourceDirection =
				sourceAttribute
						.getDirection()
						.orElseThrow(() ->
								new MappingException(
										"Flow '%s' source '%s' has no direction."
												.formatted(
														getName(),
														sourceAttribute.getName()
												)
								)
						);

		if (sourceDirection != Direction.OUT &&
				sourceDirection != Direction.INOUT) {
			throw new MappingException(
					"Flow '%s' source '%s' must have direction OUT or INOUT, but got '%s'."
							.formatted(
									getName(),
									sourceAttribute.getName(),
									sourceDirection
							)
			);
		}

		Direction targetDirection =
				targetAttribute
						.getDirection()
						.orElseThrow(() ->
								new MappingException(
										"Flow '%s' target '%s' has no direction."
												.formatted(
														getName(),
														targetAttribute.getName()
												)
								)
						);

		if (targetDirection != Direction.IN &&
				targetDirection != Direction.INOUT) {
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

	private void validateTaxonomy(
			TwinAttributeMapped<Usage> attribute,
			Taxonomy<Usage> expectedContext,
			String endpoint
	) throws MappingException {

		var actualTaxonomy =
				attribute.getTaxonomy().orElseThrow(() ->
						new MappingException(
								"Flow '%s' %s '%s' has no taxonomy."
										.formatted(
												getSysmlElement().path(),
												endpoint,
												attribute.getName()
										)
						)
				);

		if (!expectedContext.getClass()
				.isAssignableFrom(actualTaxonomy.getClass())) {
			throw new MappingException(
					"Flow '%s' %s '%s' belongs to taxonomy '%s', expected '%s'."
							.formatted(
									getSysmlElement().path(),
									endpoint,
									attribute.getName(),
									actualTaxonomy.getTaxonomy()
											.get()
											.getClass()
											.getName(),
									expectedContext.getTaxonomy()
											.get()
											.getClass()
											.getName()
							)
			);
		}
	}

	private void validateEndpointTypes(
			TwinAttributeMapped<Usage> sourceAttribute,
			TwinAttributeMapped<Usage> targetAttribute
	) throws MappingException {

		var sourceDefinition =
				sourceAttribute
						.getDefinitionOfUsage()
						.map(Reference::getReferent)
						.orElse(null);

		var targetDefinition =
				targetAttribute
						.getDefinitionOfUsage()
						.map(Reference::getReferent)
						.orElse(null);

		if (sourceDefinition == null || targetDefinition == null) {
			return;
		}

		if (!sourceDefinition.isSubtypeOf(targetDefinition)) {
			throw new MappingException(
					"Flow '%s' has incompatible endpoint types: source '%s' has type '%s', target '%s' expects '%s'."
							.formatted(
									getSysmlElement().path(),
									sourceAttribute.getName(),
									sourceDefinition.getName(),
									targetAttribute.getName(),
									targetDefinition.getName()
							)
			);
		}
	}
}