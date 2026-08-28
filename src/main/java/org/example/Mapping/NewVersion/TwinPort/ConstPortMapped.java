package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.AdditionalRoles;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinPort.Actuators;
import org.example.Mapping.Interfaces.TwinPort.ConstPort;
import org.example.Mapping.Interfaces.TwinPort.Sensors;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.CONST_PORT)
@ToString(callSuper = true)
public class ConstPortMapped<T extends TypeKind> extends TwinPortMapped<T> implements ConstPort<T> {
	private CompartmentContainerMapped<TwinAttributeMapped<Usage>> attributes;

	public ConstPortMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public CompartmentContainerMapped<? extends TwinAttribute<Usage>> getAttributes() {
		return attributes;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		attributes = context.mapSlot(this, "measurements", TwinAttributeMapped.getRawUsageClass());
	}


	protected List<AdditionalRoles> addAdditionalRoles() {
		return List.of(new AdditionalRoles(
				attributes,
				List.of(Role.CONST)
		));
	}
}
