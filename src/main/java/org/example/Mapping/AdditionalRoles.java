package org.example.Mapping;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;

import java.util.List;

public record AdditionalRoles(
		CompartmentContainerMapped<? extends TwinAttributeMapped<?>> models,
		List<Role> roles
) {
}