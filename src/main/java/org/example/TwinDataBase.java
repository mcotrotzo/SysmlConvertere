package org.example;


import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwinDataBase {

	Map<String, Model> mappedElements = new HashMap<>();


	public TwinDataBase(List<MappedElement<?>> m) {
		register(m);
	}

	private void register(MappedElement<?> m) {
		mappedElements.put(m.getId(), m);
	}

	private void register(List<MappedElement<?>> m) {
		m.forEach(this::register);
	}

	public <T extends Model> Set<T> get(Class<T> type) {
		return mappedElements.values().stream().filter(type::isInstance).map(type::cast).collect(java.util.stream.Collectors.toSet());
	}

	public <T extends Model> T getByReference(MappedReference<?> reference, Class<T> type) {
		Model target = mappedElements.get(reference.getTargetId());

		if (target == null) {
			throw new IllegalArgumentException("Unknown reference target: " + reference.getTargetId());
		}

		return type.cast(target);
	}

	public <T extends Model> T get(String id) {
		return (T) mappedElements.get(id);
	}


	public <T extends Model> Set<Class<T>> getAllTypes() {
		return (Set<Class<T>>) mappedElements.values().stream().map(x -> (Class<T>) x.getClass()).collect(java.util.stream.Collectors.toSet());
	}

}
