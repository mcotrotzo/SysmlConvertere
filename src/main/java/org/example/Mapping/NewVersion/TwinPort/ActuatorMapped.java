package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.AdditionalRoles;
import org.example.Mapping.Interfaces.Base.TypeKind.RoleClass;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinPort.Actuators;
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

@MappedElementType(LibraryNameSpaces.ACTUATOR)
@ToString(callSuper = true)
public class ActuatorMapped<T extends TypeKind> extends TwinPortMapped<T> implements Actuators<T> {
	private CompartmentContainerMapped<TwinAttributeMapped<Usage>> attributes;

	public ActuatorMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public CompartmentContainerMapped<? extends TwinAttribute<Usage>> getAttributes() {
		return attributes;
	}
	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		attributes = context.mapSlot(this, "commands", TwinAttributeMapped.getRawUsageClass());
	}

	@Override
	protected List<AdditionalRoles> addAdditionalRoles() {
		return List.of(
				new AdditionalRoles(
						attributes.getCompartment().stream().map(CompartmentMapped::getElement).toList(),
				List.of(
						new RoleClass(
								rawClassOf(Actuators.class),
								Role.ACTUATOR
						)
				)
		));
	}
}
