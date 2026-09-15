package org.example.Util;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.example.ElemWithMult;
import org.example.LoadedResources;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunctionKind;
import org.example.UtilClasses.RedefinitionGraph;
import org.example.UtilClasses.SpecialicationGraph;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;
import org.omg.sysml.util.ElementUtil;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.NamespaceUtil;
import org.omg.sysml.util.TypeUtil;

import java.lang.Class;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
public class Utils {

	private static final Set<String> TECHNICAL_KIND_OWNERS = Set.of("TwinLibraryElement", "TwinLibraryElementConnection", "TwinLibraryElementCalcDef", "TwinLibraryElementActionDef", "TwinLibraryElementAttributeDef", "TwinLibraryElementPortDef", "TwinLibraryElementStateDef");
	private final LoadedResources loadedResources;
	private final Map<LibraryNameSpaces, Type> libraryMap = new HashMap<>();
	private final Element rootElement;
	private final Set<Element> cachedElements = new HashSet<>();
	private final Map<Function, BaseFunctionKind> baseFunctionKinds = new IdentityHashMap<>();
	private Set<SpecialicationGraph<?, ?, ?>> specializationGraphs = new HashSet<>();

	public Utils(LoadedResources loadedResources) {
		this.loadedResources = loadedResources;
		this.rootElement = loadedResources.rootElement();
		initLibraryElements();
		collectAllUserElements();
	}

	public static ElemWithMult getMultiplicityRange(Type type) {
		MultiplicityRange mult = FeatureUtil.getMultiplicityRangeOf(type.getMultiplicity());
		if (mult != null) {
			int lower = mult.valueOf(mult.getLowerBound());
			int upper = mult.valueOf(mult.getUpperBound());
			if (lower < 0) lower = upper;

			return new ElemWithMult(lower, upper);
		} else {
			return new ElemWithMult(1, 1);
		}
	}



	private void initLibraryElements() {
		for (LibraryNameSpaces libraryNameSpaces : LibraryNameSpaces.values()) {
			libraryMap.put(libraryNameSpaces, SysMLLibraryUtil.getLibraryType(rootElement, String.valueOf(libraryNameSpaces)));
		}
	}

	public boolean isFromStandardOrDTLibrary(Element element) {
		return isFromStandardLibrary(element) || isFromDTLibrary(element);
	}

	public boolean isFromDTLibrary(Element element) {
		return isFromResourceLibrary(element, loadedResources.dtLibrary());
	}

	public boolean isFromStandardLibrary(Element element) {
		return ElementUtil.isStandardLibraryElement(element);
	}


	private boolean isFromResourceLibrary(Element element, Resource libraryResource) {
		if (element == null || element.eResource() == null || libraryResource == null) return false;
		return libraryResource.getURI().equals(element.eResource().getURI());
	}

	private void collectAllUserElements() {
		collectRecursively(loadedResources.userContent());
	}


	private void collectRecursively(Resource resource) {
		if (resource == null) return;
		for (TreeIterator<EObject> it = resource.getAllContents(); it.hasNext(); ) {
			EObject content = it.next();
			if (content instanceof Element element) {
				cachedElements.add(element);
			}
		}
	}

	public <T extends Element> Set<T> collect(Class<T> clazz) {
		return cachedElements.stream().filter(clazz::isInstance).map(clazz::cast).collect(Collectors.toSet());
	}

	public Type convertBasicFeatureToType(Type feature) {
		if (feature instanceof Feature featureType) {
			return (Type) FeatureUtil.getBasicFeatureOf(featureType);
		}
		return feature;
	}



	public boolean redefinesOrSubsets(Feature candidate, String targetName) {
		if (targetName == null) return true;

		Set<Feature> visited = new HashSet<>();
		Deque<Feature> toCheck = new ArrayDeque<>();
		toCheck.add(candidate);

		while (!toCheck.isEmpty()) {
			Feature current = toCheck.poll();
			if (!visited.add(current)) continue;
			if (targetName.equals(current.getName())) return true;

			for (Redefinition r : current.getOwnedRedefinition()) {
				toCheck.add(r.getRedefinedFeature());
			}
			for (Subsetting s : current.getOwnedSubsetting()) {
				toCheck.add((Feature) s.getGeneral());
			}
		}

		return false;
	}

	public Type getLibTypeFromAnnotation(LibraryNameSpaces value) {
		return libraryMap.get(value);
	}



	public boolean isTechnicalKindFeature(Feature feature) {
		if (!"kind".equals(feature.getName())) {
			return false;
		}

		Type owner = feature.getOwningType();

		return owner != null && TECHNICAL_KIND_OWNERS.contains(owner.getName());
	}


}