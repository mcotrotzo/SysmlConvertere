package org.example.Mapping.TwinAttributeMapped.CustomTypeMapped;

import lombok.ToString;
import org.example.Mapping.AdditionalRoles;
import org.example.Mapping.Interfaces.Base.TypeKind.RoleClass;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomAttributeMapped<T extends TypeKind>
		extends TwinAttributeMapped<T>
		implements CustomType<T> {

	protected CompartmentContainerMapped<TwinAttributeMapped<Usage>> fields =
			new CompartmentContainerMapped<>();

	public CustomAttributeMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public CompartmentContainerMapped<TwinAttributeMapped<Usage>> getFields() {
		return fields;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		fields = context.mapSlot(
				this,
				"fields",
				TwinAttributeMapped.getRawUsageClass()
		);
	}

	@Override
	protected List<AdditionalRoles> addAdditionalRoles() {
		return List.of(
				new AdditionalRoles(
						fields.getCompartment()
								.stream()
								.map(compartment -> compartment.getElement())
								.toList(),
						List.of(
								new RoleClass(
										rawClassOf(CustomType.class),
										Role.CUSTOM_TYPE_MEMBER
								)
						)
				)
		);
	}
}