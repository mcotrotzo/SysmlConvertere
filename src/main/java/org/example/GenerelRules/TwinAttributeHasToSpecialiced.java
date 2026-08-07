package org.example.GenerelRules;

import org.eclipse.emf.common.util.EList;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryNameSpaces;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.*;

import java.util.List;
import java.util.Set;

public class TwinAttributeHasToSpecialiced extends GenerelRules{
	public TwinAttributeHasToSpecialiced(Utils utils) {
		super(utils);
	}

	@Override
	public boolean isValid() throws MappingException {
		Set<AttributeUsage> userTypes = utilsManager.collect(AttributeUsage.class);

		for (AttributeUsage attributeUsage : userTypes) {
			validateAttribute(attributeUsage);
			validateType(attributeUsage,attributeUsage.getType());

		}

		return true;
	}

	private void validateType(
			AttributeUsage attributeUsage,
			List<Type> types
	) throws MappingException {


		Type twinAttributeType =
				utilsManager.getLibTypeFromAnnotation(
						LibraryNameSpaces.TWIN_ATTRIBUTE
				);

		boolean hasGenericTwinAttribute =
				types.stream()
						.anyMatch(type -> type == twinAttributeType);

		if (hasGenericTwinAttribute) {
			throw new MappingException(
					("Attribute '%s' is typed only through TwinAttribute, "
							+ "but TwinAttribute must be specialized.")
							.formatted(
									attributeUsage.getQualifiedName()
							)
			);
		}
	}

	private void validateAttribute(AttributeUsage attribute) throws MappingException {
		boolean hasExplicitType = !attribute.getOwnedTyping().isEmpty();

		boolean hasSubsetting = !attribute.getOwnedSubsetting().isEmpty();

		boolean hasRedefinition = !attribute.getOwnedRedefinition().isEmpty();

		if (!hasExplicitType && !hasSubsetting && !hasRedefinition) {
			throw new MappingException(
					"Attribute '%s' is freestanding and cannot be mapped."
							.formatted(attribute.getQualifiedName())
			);
		}
	}
}
