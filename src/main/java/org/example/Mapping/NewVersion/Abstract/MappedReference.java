package org.example.Mapping.NewVersion.Abstract;

import lombok.Getter;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;

public final class MappedReference<T extends MappedNamespaceElement<?>> implements Reference<T> {

	@Getter
	private final String targetId;
	@Getter
	private final T referent;

	public MappedReference(T target) {
		this.targetId = target.getId();
		this.referent = target;
	}

	@Override
	public String toString() {
		return targetId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof MappedReference<?> other)) return false;
		return targetId.equals(other.targetId);
	}

	@Override
	public int hashCode() {
		return targetId.hashCode();
	}

}
