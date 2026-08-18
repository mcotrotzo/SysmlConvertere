package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.ReadWriteRoles;
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
import org.omg.sysml.lang.sysml.util.SysMLSwitch;
import org.omg.sysml.util.TypeUtil;

import java.util.*;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public class TwinAttributeMapped extends MappedElement<Type> implements TwinAttribute {
	private List<TwinExpression<?>> twinExpressions = new ArrayList<>();
	protected MappedReference<TwinAttributeMapped> typeReference;

	private ReadWriteRoles readWriteRoles;
	public TwinAttributeMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		Class<TwinExpression<?>> rawClass = (Class<TwinExpression<?>>) (Class<?>) TwinExpression.class;
		twinExpressions =context.mapOwned(this, Expression.class, rawClass);
		resolveTypeReference(context);
	}

	@Override
	public Optional<org.example.Mapping.Interfaces.Expression> getTwinExpressions() {
		if (twinExpressions == null || twinExpressions.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(twinExpressions.iterator().next());
	}

	@Override
	public ReadWriteRoles getRole() {
		return readWriteRoles;
	}


	public void setRole(ReadWriteRoles role) {
		this.readWriteRoles = role;
	}

	protected void resolveTypeReference(MappingContext context) throws MappingException {
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
