package org.example.Mapping.NewVersion.TwinFlow.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.TaxonomyMapped;
import org.example.Mapping.TwinAction.TwinActionMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FlowDefinition;
import org.omg.sysml.lang.sysml.FlowUsage;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

@MappedElementType(LibraryNameSpaces.TWIN_FLOW)
@ToString(callSuper = true)
public class FlowMapped<T extends TypeKind> extends TwinActionMapped<Type,T> implements Flow<T> {

	private TaxonomyMapped<Usage> taxonomySource;
	private TaxonomyMapped<Usage> taxonomyTarget;

	private MappedReference<? extends TwinAttributeMapped<Usage>> source;
	private MappedReference<? extends TwinAttributeMapped<Usage>> target;

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
			throw new MappingException("Could not resolve basic feature for flow endpoint '%s'.".formatted(feature.getName()));
		}

		var redefinitions = basic.getOwnedRedefinition();

		if (redefinitions.size() != 1) {
			throw new MappingException("Flow endpoint '%s' must redefine exactly one feature, but redefines %d.".formatted(basic.getName(), redefinitions.size()));
		}

		Feature referencedFeature = redefinitions.getFirst().getRedefinedFeature();

		if (referencedFeature == null) {
			throw new MappingException("Flow endpoint '%s' has no redefined feature.".formatted(basic.getName()));
		}

		return referencedFeature;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		if (!(getSysmlElement() instanceof FlowUsage flowUsage)) return;
		if (context.getUtils().isFromDTLibrary(flowUsage)) return;

		Class<TaxonomyMapped<Usage>> taxonomyClass = rawClassOf(TaxonomyMapped.class);

		taxonomySource = context.mapPrivateSlot(this, "tax1", taxonomyClass).stream().findFirst().orElseThrow(() -> new MappingException("Flow '%s' has no source taxonomy.".formatted(getName())));

		taxonomyTarget = context.mapPrivateSlot(this, "tax2", taxonomyClass).stream().findFirst().orElseThrow(() -> new MappingException("Flow '%s' has no target taxonomy.".formatted(getName())));

		Feature sourceFeature = resolveFlowEndpoint(flowUsage.getSourceOutputFeature());
		Feature targetFeature = resolveFlowEndpoint(flowUsage.getTargetInputFeature());

		source = context.mapReference(sourceFeature, rawClassOf(TwinAttributeMapped.class));
		target = context.mapReference(targetFeature, rawClassOf(TwinAttributeMapped.class));
	}

	@Override
	public Taxonomy<Usage> sourceContexts() {
		return taxonomySource;
	}

	@Override
	public Taxonomy<Usage> targetContexts() {
		return taxonomyTarget;
	}

	@Override
	public Reference<? extends TwinAttribute<Usage>> getSource() {
		return source;
	}

	@Override
	public Reference<? extends TwinAttribute<Usage>> getTarget() {
		return target;
	}

	@Override
	public void postValidate() throws MappingException {
		super.postValidate();

		if (!(getSysmlElement() instanceof FlowUsage)) return;
		if (source == null || target == null) return;
		if (taxonomySource == null || taxonomyTarget == null) return;

		TwinAttributeMapped<Usage> sourceAttribute = source.getReferent();
		TwinAttributeMapped<Usage> targetAttribute = target.getReferent();

		validateEndpointTypes();
		validateTaxonomy(sourceAttribute, taxonomySource, "source");
		validateTaxonomy(targetAttribute, taxonomyTarget, "target");

		Direction sourceDirection = sourceAttribute.getDirection().orElseThrow(() -> new MappingException("Flow '%s' source '%s' has no direction.".formatted(getName(), sourceAttribute.getName())));

		if (sourceDirection != Direction.OUT && sourceDirection != Direction.INOUT) {
			throw new MappingException("Flow '%s' source '%s' must have direction OUT or INOUT, but got '%s'.".formatted(getName(), sourceAttribute.getName(), sourceDirection));
		}

		Direction targetDirection = targetAttribute.getDirection().orElseThrow(() -> new MappingException("Flow '%s' target '%s' has no direction.".formatted(getName(), targetAttribute.getName())));

		if (targetDirection != Direction.IN && targetDirection != Direction.INOUT) {
			throw new MappingException("Flow '%s' target '%s' must have direction IN or INOUT, but got '%s'.".formatted(getName(), targetAttribute.getName(), targetDirection));
		}
	}

	private void validateTaxonomy(TwinAttributeMapped<Usage> attribute, Taxonomy<?> expected, String endpoint) throws MappingException {

		Class<? extends Taxonomy> actual = attribute.getTaxonomy();

		Class<? extends Taxonomy> expectedClass = expected.getTaxonomy();

		if (!expectedClass.isAssignableFrom(actual)) {
			throw new MappingException(
					"Flow '%s' %s '%s' has taxonomy '%s', expected '%s' (or a specialization of it)."
							.formatted(this.sysmlElement.path(), endpoint, attribute.getName(),
									actual.getSimpleName(), expectedClass.getSimpleName())
			);
		}
	}

	private void validateEndpointTypes() throws MappingException {
		if (source == null || target == null) return;

		var sourceDefinition = source.getReferent()
				.getDefinitionOfUsage()
				.map(Reference::getReferent)
				.orElse(null);

		var targetDefinition = target.getReferent()
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
									source.getReferent().getName(),
									sourceDefinition.getName(),
									target.getReferent().getName(),
									targetDefinition.getName()
							)
			);
		}
	}
}