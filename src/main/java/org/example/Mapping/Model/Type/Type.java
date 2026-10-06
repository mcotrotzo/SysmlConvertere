package org.example.Mapping.Model.Type;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Model;

import org.example.Util.TwinLibraryNamespace;

import java.util.Arrays;
import java.util.function.Supplier;

public abstract class Type<T extends org.omg.sysml.lang.sysml.Type, C extends Core<? super T>> extends Model<T> implements CoreApi<C> {

	private C core;

	public Type(T sysmlElement, Supplier<C> coreFactory, Mapper newMappe) {
		super(sysmlElement, newMappe);
		this.core = coreFactory.get();
	}

	@Override public void fillSlots() { getCore().fillSlots(this); }

	@Override
	public C getCore() {
		return core;
	}

	public boolean isTwinLibraryRoot(org.omg.sysml.lang.sysml.Type type) {
		String qn = type.getQualifiedName();

		return Arrays.stream(TwinLibraryNamespace.values())
				.anyMatch(root -> root.toString().equals(qn));
	}
}
