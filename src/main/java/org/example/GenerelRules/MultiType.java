package org.example.GenerelRules;

import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.Usage;
import org.omg.sysml.util.TypeUtil;

import java.util.HashSet;
import java.util.Set;

public class MultiType extends GenerelRules{
	public MultiType(Utils utils) {
		super(utils);
	}

	@Override
	public boolean isValid() throws MappingException {
		Set<Feature> userTypes = utilsManager.collect(Feature.class);

		for (Feature userType : userTypes) {
			if (!(userType instanceof Usage || userType instanceof Definition)) {
				continue;
			}
			validateTypes(userType);
		}
		return true;
	}

	private void validateTypes(Feature element) throws MappingException {
		Set<Type> effectiveTypes = new HashSet<>(element.getType());
		for (Type a : effectiveTypes) {
			for (Type b : effectiveTypes) {
				if (a == b) {
					continue;
				}

				boolean related =
						TypeUtil.specializes(a, b)
								|| TypeUtil.specializes(b, a);

				if (!related) {
					throw new MappingException(
							("Type '%s' has incompatible typings '%s' and '%s'.")
									.formatted(
											element.getQualifiedName(),
											a.getName(),
											b.getName()
									)
					);
				}
			}
		}
	}
}
