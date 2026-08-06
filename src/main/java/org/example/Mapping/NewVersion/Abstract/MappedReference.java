package org.example.Mapping.NewVersion.Abstract;

import lombok.Getter;
import org.example.Mapping.Interfaces.Reference;

public final class MappedReference<T extends MappedElement<?>> implements Reference<T> {

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
