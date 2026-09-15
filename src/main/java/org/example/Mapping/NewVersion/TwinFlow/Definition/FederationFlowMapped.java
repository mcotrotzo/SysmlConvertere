package org.example.Mapping.NewVersion.TwinFlow.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumAttribute;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumFederationLink;
import org.example.Mapping.Interfaces.TwinFlow.FederationFlow;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeIntegerMapped;
import org.example.Mapping.TwinAttributeMapped.EnumFederationLinkMapped;
import org.example.Mapping.TwinAttributeMapped.EnumTimeUnitMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;
import org.omg.sysml.lang.sysml.FlowUsage;

import java.util.Optional;

@MappedElementType(value = LibraryNameSpaces.FEDERATION_LINK_FLOW)
@ToString(callSuper = true)
public class FederationFlowMapped<T extends TypeKind> extends FlowMapped<T> implements FederationFlow<T> {

	private Optional<CompartmentMapped<EnumFederationLinkMapped>> linkTypeMapped =
			Optional.empty();


	@Override
	public Optional<? extends Compartment<? extends EnumAttribute<EnumFederationLink>>> linkType() {
		return linkTypeMapped;
	}

	public FederationFlowMapped(FlowDefinition sysmlElement) {
		super(sysmlElement);
	}

	public FederationFlowMapped(FlowUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		linkTypeMapped = parseOptionalSlot(
				context,
				"linkType",
				EnumFederationLinkMapped.class
		);

	}
	private <E extends MappedElement<?, Usage>>
	Optional<CompartmentMapped<E>> parseOptionalSlot(
			MappingContext context,
			String slotName,
			Class<E> clazz
	) throws MappingException {

		var values = context
				.mapSlot(this, slotName, clazz)
				.getCompartment();

		if (values.size() > 1) {
			throw new MappingException(
					"%s '%s': slot '%s' may be specified at most once, but found %d."
							.formatted(
									getClass().getSimpleName(),
									getName(),
									slotName,
									values.size()
							)
			);
		}

		return values.stream().findFirst();
	}

}
