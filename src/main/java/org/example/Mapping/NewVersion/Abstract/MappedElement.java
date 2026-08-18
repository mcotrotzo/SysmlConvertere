package org.example.Mapping.NewVersion.Abstract;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.example.ElemWithMult;
import org.example.Mapping.Interfaces.KIND;
import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.Interfaces.ReadWriteRoles;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

import java.nio.charset.StandardCharsets;
import java.util.*;

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

	@Getter
	private String deterministicId;


	public MappedElement(T sysmlElement) {
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


	@Override
	public Set<ReadWriteRoles> getReadPermissions() {
		return new HashSet<>();
	}

	@Override
	public Set<ReadWriteRoles> getWritePermissions() {
		return new HashSet<>();
	}

}
