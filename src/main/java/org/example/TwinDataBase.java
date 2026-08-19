package org.example;


import org.example.Mapping.Interfaces.KIND;
import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.Packages.MappedNamespaceElement;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.impl.FeatureImpl;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

import java.util.*;
import java.util.stream.Collectors;

public class TwinDataBase {

	private Map<String, Model> mappedElements = new HashMap<>();
	private List<MappedNamespaceElement<?>> mappedRaWElementzs = new ArrayList<>();

	public TwinDataBase(List<MappedNamespaceElement<?>> m) {
		this.mappedRaWElementzs =m;
		register(m);
	}

	private void register(MappedNamespaceElement<?> m) {
		mappedElements.put(m.getId(), m);
	}

	private void register(List<MappedNamespaceElement<?>> m) {
		m.forEach(this::register);
	}

	public <T extends Model> Set<T> get(Class<T> type) {
		return mappedElements.values().stream().filter(type::isInstance).map(type::cast).collect(java.util.stream.Collectors.toSet());
	}

	public  Set<Model> getAll() {
		return new HashSet<>(mappedElements.values());
	}

	public <T extends Model> T getByReference(Reference<?> reference, Class<T> type) {
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


	public List<Model> getSpecializationChildren(Model target) {

		if (!(target instanceof MappedElement<?> mappedTarget)) {
			throw new IllegalArgumentException(
					"Model element must be a mapped element"
			);
		}

		if (target.getKind().equals(KIND.DEFINITION)) {
			throw new IllegalArgumentException(
					"Model element must be a usage"
			);
		}


		return mappedRaWElementzs.stream()
				.filter(mapped -> mapped != mappedTarget)
				.filter(mapped ->
						TypeUtil.getSupertypesOf((Type) mapped.getSysmlElement(),
								true
						).contains(mappedTarget.getSysmlElement())
				)
				.map(mapped -> (Model) mapped)
				.toList();
	}

	public ElemWithMult getMultiplicity(Model target) {
		if (!(target instanceof MappedElement<?> mappedTarget)) {
			throw new IllegalArgumentException(
					"Model element must be a mapped element"
			);
		}

		if (target.getKind().equals(KIND.DEFINITION)) {
			throw new IllegalArgumentException(
					"Model element must be a usage"
			);
		}

		return Utils.getMultiplicityRange(mappedTarget.getSysmlElement());
	}

}
