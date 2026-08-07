package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.Usage;
import org.omg.sysml.util.TypeUtil;

import java.util.*;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public class TwinAttributeMapped extends MappedElement<Type> implements TwinAttribute {
	private Set<TwinExpression<?>> twinExpressions;
	private MappedReference<TwinAttributeMapped> typeReference;

	public TwinAttributeMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		Class<TwinExpression<?>> rawClass = (Class<TwinExpression<?>>) (Class<?>) TwinExpression.class;
		twinExpressions =new HashSet<>(context.mapOwned(this, Expression.class, rawClass));
		resolveTypeReference(context);
	}

	@Override
	public Optional<org.example.Mapping.Interfaces.Expression> getTwinExpressions() {
		return Optional.of(twinExpressions.stream().findFirst().get());
	}

	@Override
	public Optional<Reference<? extends TwinAttribute>> getDefinitionReference() {
		return Optional.ofNullable(typeReference);
	}

	private void resolveTypeReference(MappingContext context) throws MappingException {
		if (!(getSysmlElement() instanceof Usage)) {
			return;
		}

		var supertypes = TypeUtil.getSupertypesOf(getSysmlElement());

		Type target = supertypes.stream().filter(type -> !context.getUtils().isFromStandardOrDTLibrary(type)).findFirst().or(() -> supertypes.stream().findFirst()).orElse(null);

		if (target == null) {
			throw new MappingException("%s is not typed but a TwinAttribute has to be typed".formatted(getName()));
		}

		typeReference = context.mapReference(target, TwinAttributeMapped.class);
	}


}
