package org.example.Mapping.Interfaces.Base.TypeKind;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;

import java.util.HashSet;
import java.util.Set;

public record RoleClass(
		Class<? extends Model<?>> modelClass,
		Set<Role> additionalRole
) {

	public RoleClass(Class<? extends Model<?>> modelClass) {
		this(modelClass, new HashSet<>());
	}

	public RoleClass(
			Class<? extends Model<?>> modelClass,
			Role role
	) {
		this(modelClass, new HashSet<>(Set.of(role)));
	}
}

