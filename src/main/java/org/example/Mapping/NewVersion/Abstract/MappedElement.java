package org.example.Mapping.NewVersion.Abstract;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.Mapping.Interfaces.KIND;
import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.Type;

import java.util.Objects;
import java.util.Optional;

@ToString(of = {"name", "id"})
public abstract class MappedElement<T extends Type> implements Model {

	@Getter
	protected T sysmlElement;

	@Getter
	@Setter
	private MappedElement<?> owner;

	@Getter
	private String name;

	@Getter
	private String id;

	public MappedElement(T sysmlElement) {
		bindSysmlElement(sysmlElement);
	}

	private void bindSysmlElement(T sysmlElement) {
		this.sysmlElement = Objects.requireNonNull(sysmlElement);
		name = sysmlElement.getName();
		id = sysmlElement.getElementId();

	}

	public abstract void parse(MappingContext context) throws MappingException;

	@Override
	public Optional<Model> getParent() {
		return Optional.ofNullable(owner).map(Model.class::cast);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof MappedElement<?> other)) return false;
		return sysmlElement == other.sysmlElement;
	}

	@Override
	public final int hashCode() {
		return System.identityHashCode(sysmlElement);
	}

	@Override
	public KIND getKind() {
		return sysmlElement instanceof Definition ? KIND.DEFINITION : KIND.USAGE;
	}

}
