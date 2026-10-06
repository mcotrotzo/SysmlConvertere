package org.example.Mapping.Model.Type;

import lombok.Getter;
import org.example.ElemWithMult;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Slots;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.util.TypeUtil;

import java.lang.Class;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class Usage<C extends Core<? super U>, U extends Feature,D extends Definition> extends Type<U, C> {

	@Getter private D definition;
	private final Class<D> definitionClass;

	@Getter private ElemWithMult multiplicity;
	@Getter private List<Usage<?, ?, ?>> specializations = List.of();

	protected Usage(U sysmlElement, Supplier<C> coreFactory, Mapper newMappe, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, newMappe);
		this.definitionClass = definitionClass;
		multiplicity = newMappe.getNewUtil().getMultiplicityRange(this.getSysmlElement());
		specializations = mapSpecializations();
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		List<Classifier> definitions = sysmlElement.getType().stream()
				.filter(Classifier.class::isInstance).map(Classifier.class::cast)
				.filter(x -> !instance.getNewUtil().isFromStandardLibrary(x)).toList();
		if (definitions.isEmpty()){
			return;
		}
		Classifier def = mostSpecificDefinition(definitions);
		if (isTwinLibraryRoot(def)) {
			return;
		}
		// definitions exist once: mapped without owner; a definition of another kind fails with a clear message
		definition = instance.map(def, null, definitionClass);
	}

	private Classifier mostSpecificDefinition(List<Classifier> definitions){
		List<Classifier> mostSpecific = definitions.stream().filter(candidate -> definitions.stream().filter(other -> other != candidate).noneMatch(other -> TypeUtil.getSupertypesOf(other, true).contains(candidate))).toList();

		if (mostSpecific.size() == 1) {
			return mostSpecific.getFirst();
		}

		if (mostSpecific.isEmpty()) {
			throw new IllegalArgumentException("No most specific definition found.");
		}

		throw new IllegalArgumentException("Multiple unrelated definitions found: " + mostSpecific.stream().map(Classifier::getQualifiedName).toList());
	}

	private List<Usage<?, ?, ?>> mapSpecializations() {
		Class<Usage<?, ?, ?>> usageClass = Slots.rawClassOf(Usage.class);
		List<Usage<?, ?, ?>> result = new ArrayList<>();
		for (Subsetting subsetting : sysmlElement.getOwnedSubsetting()) {
			if (subsetting instanceof ReferenceSubsetting) {
				continue;
			}
			Feature general = subsetting.getSubsettedFeature();
			if (general == null || instance.getNewUtil().isfromLibraryRawType(general) || instance.getNewUtil().isFromStandardLibrary(general)) {
				continue;
			}
			result.add(instance.mapChain(general.getChainingFeature().isEmpty() ? List.of(general) : general.getChainingFeature(), this, usageClass));
		}
		return result;
	}

	public Optional<FeatureDirectionKind> getDirection() {
		FeatureDirectionKind direction = sysmlElement.getDirection();
		if (direction == null) return Optional.empty();
		return Optional.of(direction);
	}



}
