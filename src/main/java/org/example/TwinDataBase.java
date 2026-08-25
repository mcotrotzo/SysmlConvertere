package org.example;


import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;

import java.util.*;

public class TwinDataBase {

	private final Map<String, Model<?>> mappedElements = new HashMap<>();
	private List<MappedNamespaceElement<?, ?>> mappedRaWElementzs = new ArrayList<>();

	public TwinDataBase(List<MappedNamespaceElement<?, ?>> m) {
		this.mappedRaWElementzs = m;
		register(m);
	}

	private void register(MappedNamespaceElement<?, ?> m) {
		mappedElements.put(m.getId(), m);
	}

	private void register(List<MappedNamespaceElement<?, ?>> m) {
		m.forEach(this::register);
	}

	@SuppressWarnings("unchecked")
	public <T extends Model<Z>, Z extends TypeKindNamespace> Set<T> get(Class<? super T> type, Class<Z> typeKind) {
		return mappedElements.values().stream().filter(type::isInstance).filter(x -> typeKind.isInstance(x.getKind())).map(x -> (T) x).collect(java.util.stream.Collectors.toSet());
	}

	public Set<Model<?>> getAll() {
		return new HashSet<>(mappedElements.values());
	}

	public <T extends Model<TypeKind>> T getByReference(Reference<?> reference, Class<T> type) {
		Model<?> target = mappedElements.get(reference.getTargetId());
		if (target == null) throw new IllegalArgumentException("Unknown reference target: " + reference.getTargetId());
		return type.cast(target);
	}

	@SuppressWarnings("unchecked")
	public <T extends Model<?>> T get(String id) {
		return (T) mappedElements.get(id);
	}

	public <T extends Model<?>> Set<Class<T>> getAllTypes() {
		return (Set<Class<T>>) mappedElements.values().stream().map(x -> (Class<T>) x.getClass()).collect(java.util.stream.Collectors.toSet());
	}


	public List<Model<Usage>> getSpecializationChildren(Model<Usage> target) {
		if (!(target instanceof MappedElement<?, ?> mappedTarget))
			throw new IllegalArgumentException("Model element must be a mapped element");
		if (!(mappedTarget.getSysmlElement() instanceof Type targetType))
			throw new IllegalArgumentException("Model element must wrap a SysML Type");

		return mappedRaWElementzs.stream().filter(mapped -> mapped != mappedTarget).filter(mapped -> mapped.getKind() instanceof Usage).filter(mapped -> mapped.getSysmlElement() instanceof Type).filter(mapped -> TypeUtil.getSupertypesOf((Type) mapped.getSysmlElement(), true).contains(targetType)).map(this::asUsageModel).toList();
	}

	@SuppressWarnings("unchecked")
	private Model<Usage> asUsageModel(MappedNamespaceElement<?, ?> mapped) {
		return (Model<Usage>) mapped;
	}

	public ElemWithMult getMultiplicity(Model<Usage> target) {
		if (!(target instanceof MappedElement<?, ?> mappedTarget)) {
			throw new IllegalArgumentException("Model element must be a mapped element");
		}

		return Utils.getMultiplicityRange(mappedTarget.getSysmlElement());
	}

}
