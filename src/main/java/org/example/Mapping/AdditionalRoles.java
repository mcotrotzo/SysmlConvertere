package org.example.Mapping;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.RoleClass;

import java.util.List;

public record AdditionalRoles(
		List<? extends Model<?>> models,
		List<RoleClass> roles
) {}