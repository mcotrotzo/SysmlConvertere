package org.example.Mapping.Model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.Util.LibraryNameSpaces;
import org.example.Util.NewUtil;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Package;
import org.omg.sysml.lang.sysml.Type;


import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class Mapper {


	LibClassRegistry libClassRegistry;
	@Getter
	NewUtil newUtil;

	@Getter private Map<UUID, AbstractModel<?>> mapperMap = new HashMap<>();


	public Mapper(NewUtil newUtil) {
		this.newUtil = newUtil;
		this.libClassRegistry = new LibClassRegistry(newUtil.getLibraryRawType().getLibraries());
	}

	public void parse() {
		start(newUtil.getLibraryRawType().getRootElement());
		start(newUtil.getUserRawType().getRootElement());


	}

	public void start(org.omg.sysml.lang.sysml.Element element) {
		for (org.omg.sysml.lang.sysml.Element el : element.getOwnedElement()) {
			if (el instanceof Package) {
				start(el);
			}
			if (el instanceof Type elType) {
				map(elType, null);
			}
		}
	}

	private UUID idCalculation(Type sysmlElement, AbstractModel owner) {
		String path = sysmlElement.path();
		if (owner != null) {
			path = owner.getId() + "|" + path;
		}
		return UUID.nameUUIDFromBytes(path.getBytes(StandardCharsets.UTF_8));

	}

	public <T extends AbstractModel> T map(Type sysmlElement, AbstractModel owner, Class<T> type) {
		AbstractModel mapped = map(sysmlElement, owner);
		if (!type.isInstance(mapped)) {
			throw new IllegalStateException("Element '%s' mapped as %s, expected %s".formatted(sysmlElement.getName(), mapped == null ? "nothing" : mapped.getClass().getSimpleName(), type.getSimpleName()));
		}
		return type.cast(mapped);
	}

	public AbstractModel map(Type sysmlElement, AbstractModel owner) {
		for (AbstractModel<?> p = owner; p != null; p = p.getParent().orElse(null)) {
			if (p.getSysmlElement() == sysmlElement) {
				throw new IllegalStateException("Recursive structure: '%s' contains itself (in '%s')"
						.formatted(sysmlElement.getName(), owner.getName()));
			}
		}
		UUID id = idCalculation(sysmlElement, owner);
		AbstractModel existing = mapperMap.get(id);
		if (existing != null) return existing;

		LibraryNameSpaces t = libClassRegistry.match(sysmlElement);
		AbstractModel created = t != null ? libClassRegistry.getInstance(t, sysmlElement, this) : libClassRegistry.getMetaInstance(sysmlElement, this);
		if (created == null) return null;

		created.setId(id);
		created.setParent(owner);
		created.setInherited(owner != null && (owner.isInherited() || !isOwnedBy(sysmlElement, owner.getSysmlElement())));		created.setLibrary(newUtil.isfromLibraryRawType(sysmlElement));
		mapperMap.put(id, created);
		if (!created.isLibrary()) {
			created.fillSlots();
		}
		return created;
	}
	private boolean isOwnedBy(Element element, Element owner) {
		for (Element current = element.getOwner(); current != null; current = current.getOwner()) {
			if (current == owner) {
				return true;
			}
		}
		return false;
	}

	public <T extends AbstractModel> List<T> mapSlot(String slotName, AbstractModel owner, Class<T> type) {
		List<T> result = new ArrayList<>();
		for (Feature feature : owner.getSysmlElement().getFeature()) {

			if (newUtil.isfromLibraryRawType(feature)){
				if(!owner.isLibrary){
					continue;
				}
			}
			if (!newUtil.redefinesOrSubsets(feature, slotName)){
				continue;
			}

			AbstractModel mapped = map(feature, owner);
			if (!type.isInstance(mapped)) {
				throw new IllegalStateException("Slot '%s': '%s' mapped as %s, expected %s".formatted(slotName, feature.getName(), mapped == null ? "nothing" : mapped.getClass().getSimpleName(), type.getSimpleName()));
			}
			result.add(type.cast(mapped));
		}
		return result;

	}

	public <T extends AbstractModel> T mapSingleSlot(String slotName, AbstractModel owner, Class<T> type) {
		List<T> result = mapSlot(slotName, owner, type);
		if (result.size() > 1) {
			throw new IllegalStateException("Slot '%s' has multiple mappings: %s".formatted(slotName, result));
		}
		return result.isEmpty() ? null : result.get(0);
	}

	public <T extends AbstractModel,S extends Type> List<T> mapOwnedElement(Class<S> sysmlMetaclass, AbstractModel owner, Class<T> type) {
		List<T> result = new ArrayList<>();

		for (org.omg.sysml.lang.sysml.Element member : owner.getSysmlElement().getOwnedMember()) {
			if (!sysmlMetaclass.isInstance(member)) {
				continue;
			}
			S typedMember = sysmlMetaclass.cast(member);

			AbstractModel mapped = map(typedMember, owner);

			if (type.isInstance(mapped)) {
				result.add(type.cast(mapped));
			}
			else {
				throw new IllegalArgumentException("Owned element '%s' mapped as %s, expected %s".formatted(typedMember.getName(), mapped == null ? "nothing" : mapped.getClass().getSimpleName(), type.getSimpleName()));
			}
		}

		return  result;
	}


	public <T extends AbstractModel<?>> T mapChain(List<Feature> chain, AbstractModel<?> context, Class<T> type) {
		Feature first = chain.getFirst();
		AbstractModel<?> scope = context;
		while (scope != null && !scope.getSysmlElement().getFeature().contains(first)){
			scope = scope.getParent().orElse(null);
		}
		AbstractModel<?> current = map(first, scope);
		for (Feature link : chain.subList(1, chain.size())) {
			if (current == null) break;
			current = map(link, current);
		}
		if (!type.isInstance(current)) throw new IllegalStateException("Chain %s mapped as %s, expected %s"
				.formatted(chain.stream().map(Feature::getName).toList(), current == null ? "nothing" : current.getClass().getSimpleName(), type.getSimpleName()));
		return type.cast(current);
	}



}
