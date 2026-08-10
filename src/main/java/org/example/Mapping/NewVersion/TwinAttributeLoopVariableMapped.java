package org.example.Mapping.NewVersion;

import org.omg.sysml.lang.sysml.ReferenceUsage;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;

public class TwinAttributeLoopVariableMapped extends TwinAttributeMapped{
	public TwinAttributeLoopVariableMapped(Type sysmlElement) {
		super(sysmlElement);
	}


	@Override
	protected void resolveTypeReference(MappingContext context) throws MappingException {

		var supertypes = TypeUtil.getSupertypesOf(getSysmlElement());

		Type target = supertypes.stream()
				.filter(type -> !(type instanceof ReferenceUsage))
				.findFirst()
				.orElse(null);

		if (target == null) {
			throw new MappingException(
					"%s is not typed".formatted(getName())
			);
		}

		typeReference = context.mapReference(
				target,
				TwinAttributeMapped.class
		);
	}
}
