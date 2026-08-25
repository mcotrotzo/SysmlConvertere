package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.NameSpace;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.Element;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@ToString(of = {"name", "id"})
public abstract class MappedNamespaceElement<T extends Element, Z extends TypeKindNamespace> implements NameSpace<Z> {

	@Getter
	protected T sysmlElement;


	@Getter
	@Setter
	private MappedNamespaceElement<?, ?> owner;

	@Getter
	private String name;


	private String id;

	@Getter
	private String deterministicId;


	public MappedNamespaceElement(T sysmlElement) {
		bindSysmlElement(sysmlElement);
	}

	@SuppressWarnings("unchecked")
	protected static <M> Class<M> rawClassOf(Class<?> rawClass) {
		return (Class<M>) rawClass;
	}

	private void bindSysmlElement(T sysmlElement) {
		this.sysmlElement = Objects.requireNonNull(sysmlElement);
		name = sysmlElement.getName();
		id = sysmlElement.getElementId();
		String path = sysmlElement.path();

		deterministicId = UUID.nameUUIDFromBytes(path.getBytes(StandardCharsets.UTF_8)).toString();

	}

	public abstract void parse(MappingContext context) throws MappingException;

	;

	public void postValidate() throws MappingException {
	}

	@Override
	public Optional<? extends Model<?>> getParent() {
		return Optional.ofNullable(owner);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof MappedNamespaceElement<?, ?> other)) return false;
		return sysmlElement == other.sysmlElement;
	}

	@Override
	public final int hashCode() {
		return System.identityHashCode(sysmlElement);
	}

	@Override
	public TypeKindNamespace getKind() {
		return TypeKindNamespace.of(getSysmlElement());
	}

	@Override
	public Optional<Reference<? extends Taxonomy<Definition>>> getTaxonomy() {
		return Optional.empty();
	}

	public void postParse(MappingContext mappingContext) {
	}
}
