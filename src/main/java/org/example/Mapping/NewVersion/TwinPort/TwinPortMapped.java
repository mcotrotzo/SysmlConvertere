package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;
import org.example.Mapping.Interfaces.TwinPort.TwinPort;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeStringMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.Optional;

@MappedElementType(LibraryNameSpaces.TWIN_PORT)
@ToString(callSuper = true)
public class TwinPortMapped<Z extends TypeKind>
		extends MappedElement<Type, Z>
		implements TwinPort<Z> {
	private CompartmentMapped<TwinBaseAttributeStringMapped<Usage>> deviceKeyId;
	private Optional<CompartmentMapped<CommunicationProtocolMapped<Usage>>> protocol = Optional.empty();

	public TwinPortMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		CompartmentContainerMapped<CommunicationProtocolMapped<Usage>> protocols = context.mapSlot(this, "communicationProtocol", rawClassOf(CommunicationProtocolMapped.class));

		if (protocols.getCompartment().size() > 1) {
			throw new MappingException("TwinPort '%s': slot 'communicationProtocol' may be specified at most once, but found %d.".formatted(getName(), protocols.getCompartment().size()));
		}

		protocol = protocols.getCompartment().stream().findFirst();
		deviceKeyId = context.mapSlot(this, "deviceKey", TwinBaseAttributeStringMapped.getRawStringUsageClass()).getCompartment().stream().findFirst().orElseThrow(() -> new MappingException("TwinPort '%s': slot 'deviceKey' is required but not found.".formatted(getName())));
	}

	@Override
	public Optional<? extends CompartmentMapped<? extends CommunicationProtocolMapped<Usage>>> getProtocol() {
		return protocol;
	}

	@Override
	public CompartmentContainerMapped<? extends TwinAttribute<Usage>> getAttributes() {
		return new CompartmentContainerMapped<>();
	}

	@Override
	public Compartment<? extends TwinBaseString<Usage>> getDeviceKeyId() {
		return deviceKeyId;
	}

}