package org.example;

import org.example.Mapping.Model.AbstractModel;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class TwinDataBase {

	private final Map<UUID, AbstractModel<?>> elements = new LinkedHashMap<>();

	public TwinDataBase(Collection<? extends AbstractModel<?>> elements) {
		for (AbstractModel<?> m : elements) {
			this.elements.put(m.getId(), m);
		}
	}

	public List<AbstractModel<?>> getAll() {
		return List.copyOf(elements.values());
	}

	public Optional<AbstractModel<?>> getById(UUID id) {
		return Optional.ofNullable(elements.get(id));
	}

	public <T extends AbstractModel<?>> Optional<T> getById(UUID id, Class<T> type) {
		return getById(id).filter(type::isInstance).map(type::cast);
	}

	public <T extends AbstractModel<?>> List<T> getByType(Class<T> type) {
		return elements.values().stream().filter(type::isInstance).map(type::cast).toList();
	}
}