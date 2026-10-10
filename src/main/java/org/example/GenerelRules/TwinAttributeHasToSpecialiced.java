package org.example.GenerelRules;


import Executor.GenerelRules;
import Mapper.NewUtil;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.AttributeUsage;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;
import java.util.Set;

public class TwinAttributeHasToSpecialiced extends GenerelRules {
	public TwinAttributeHasToSpecialiced(NewUtil utils) {
		super(utils);
	}

	@Override
	public boolean isValid() throws IllegalArgumentException {
		Set<AttributeUsage> userTypes = utilsManager.collect(AttributeUsage.class);

		for (AttributeUsage attributeUsage : userTypes) {
			validateAttribute(attributeUsage);
			validateType(attributeUsage, attributeUsage.getType());

		}

		return true;
	}

	private void validateType(AttributeUsage attributeUsage, List<Type> types) throws IllegalArgumentException {


		Type twinAttributeType = utilsManager.getResourceContainer().getLibraryResources().getLibraries().get(LibraryNameSpaces.TWIN_ATTRIBUTE);

		boolean hasGenericTwinAttribute = types.stream().anyMatch(type -> type == twinAttributeType);

		if (hasGenericTwinAttribute) {
			throw new IllegalArgumentException(("Attribute '%s' is typed only through TwinAttribute, " + "but TwinAttribute must be specialized.").formatted(attributeUsage.getQualifiedName()));
		}
	}

	private void validateAttribute(AttributeUsage attribute) throws IllegalArgumentException {
		boolean hasExplicitType = !attribute.getOwnedTyping().isEmpty();

		boolean hasSubsetting = !attribute.getOwnedSubsetting().isEmpty();

		boolean hasRedefinition = !attribute.getOwnedRedefinition().isEmpty();

		if (!hasExplicitType && !hasSubsetting && !hasRedefinition) {
			throw new IllegalArgumentException("Attribute '%s' is freestanding and cannot be mapped.".formatted(attribute.getQualifiedName()));
		}
	}
}
