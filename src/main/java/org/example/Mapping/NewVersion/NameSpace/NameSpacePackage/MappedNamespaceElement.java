package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.Mapping.Interfaces.Base.NameSpace;
import org.example.Mapping.Interfaces.Base.TypeKind.NamespaceKind;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.KIND;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.Package;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.*;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@ToString(of = {"name", "id"})
public abstract class MappedNamespaceElement<T extends Element,Z extends TypeKindNamespace> implements NameSpace<Z> {

	@Getter
	protected T sysmlElement;



	@Getter
	@Setter
	private MappedNamespaceElement<?,?> owner;

	@Getter
	private String name;


	private String id;

	@Getter
	private String deterministicId;


	public MappedNamespaceElement(T sysmlElement) {
		bindSysmlElement(sysmlElement);
	}

	private void bindSysmlElement(T sysmlElement) {
		this.sysmlElement = Objects.requireNonNull(sysmlElement);
		name = sysmlElement.getName();
		id = sysmlElement.getElementId();
		String path = sysmlElement.path();

		deterministicId = UUID.nameUUIDFromBytes(
				path.getBytes(StandardCharsets.UTF_8)
		).toString();

	}

	public abstract void parse(MappingContext context) throws MappingException;

	public void postValidate() throws MappingException {};

	@Override
	public Optional<Model<?>> getParent() {
		return Optional.ofNullable(owner).map(Model.class::cast);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof MappedNamespaceElement<?,?> other)) return false;
		return sysmlElement == other.sysmlElement;
	}

	@Override
	public final int hashCode() {
		return System.identityHashCode(sysmlElement);
	}

	@Override
	public KIND getKind() {
		if(sysmlElement instanceof Classifier) {
			return KIND.DEFINITION;
		}
		if(sysmlElement instanceof Usage){
			return KIND.USAGE;
		}
		if(sysmlElement instanceof Import){
			return KIND.IMPORT;
		}
		if(sysmlElement instanceof Namespace){
			return KIND.NAMESPACE;
		}
		throw new IllegalArgumentException("Unknown kind for sysmlElement: " + sysmlElement.getClass().getName());
	}
}
