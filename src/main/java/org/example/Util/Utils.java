package org.example.Util;

import lombok.Getter;
import lombok.Setter;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.example.ElemWithMult;
import org.example.Mapping.LibraryElement;
import org.example.Mapping.Raw;
import org.example.UtilClasses.SpecialicationGraph;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;
import org.omg.sysml.util.ElementUtil;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

import java.lang.Class;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Setter
@Getter
public class Utils {

    public Utils(Element rootElement) {
        initBaseElements(rootElement);
        initLibraryElements(rootElement);

    }

    private void initBaseElements(Element rootElement){
        this.rootElement = rootElement;
        this.resourceSet = rootElement.eResource().getResourceSet();
    }

    private void initLibraryElements(Element rootElement){
        for(LibraryNameSpaces libraryNameSpaces : LibraryNameSpaces.values()){
            libraryMap.put(libraryNameSpaces,SysMLLibraryUtil.getLibraryType(rootElement, String.valueOf(libraryNameSpaces)));
        }
    }

    private ResourceSet resourceSet;
    private Element rootElement;
    private final Map<LibraryNameSpaces,Type> libraryMap = new HashMap<>();
    private Map<Element,Boolean> elementsPartOfLibraryNamespaceCache = new HashMap<>();
    private Map<String,Set<Element>> elementsCache = new HashMap<>();


    private boolean isFromLibrary(Element element) {
        if (element == null) {
            return false;
        }
        Boolean cached = elementsPartOfLibraryNamespaceCache.get(element);
        if (cached != null) {
            return cached;
        }

        List<Element> visited = new ArrayList<>();
        Element current = element;
        boolean result = false;

        while (current != null) {
            Boolean known = elementsPartOfLibraryNamespaceCache.get(current);
            if (known != null) {
                result = known;
                break;
            }
            visited.add(current);
            if (current.getOwningNamespace() instanceof LibraryPackage) {
                result = true;
                break;
            }
            current = current.getOwningNamespace();
        }

        for (Element e : visited) {
            elementsPartOfLibraryNamespaceCache.put(e, result);
        }
        return result;
    }



    public <T extends Element> Set<T> collect(Class<T> clazz, boolean excludeLibrary) {
        ensureCollected(clazz);
        String cacheKey = excludeLibrary ? clazz.getName() + ":noLib" : clazz.getName() + ":all";
        return (Set<T>) elementsCache.get(cacheKey);
    }

    public <T extends Element> Set<T> collect(Class<T> clazz) {
        return collect(clazz, true);
    }

    private void ensureCollected(Class<?> clazz) {
        String allKey = clazz.getName() + ":all";
        if (elementsCache.containsKey(allKey)) {
            return;
        }

        Set<Element> all = new HashSet<>();
        Set<Element> noLib = new HashSet<>();

        for (Resource resource : resourceSet.getResources()) {
            for (TreeIterator<EObject> it = resource.getAllContents(); it.hasNext(); ) {
                EObject content = it.next();
                if (!clazz.isInstance(content)) {
                    continue;
                }
                Element element = (Element) content;
                all.add(element);
                if (!isFromLibrary(element)) {
                    noLib.add(element);
                }
            }
        }

        elementsCache.put(allKey, all);
        elementsCache.put(clazz.getName() + ":noLib", noLib);
    }

    public Type convertBasicFeatureToType(Type feature) {
        if (feature instanceof Feature featureType) {
            return (Type) FeatureUtil.getBasicFeatureOf(featureType);
        }
        return feature;
    }

    public Set<Feature> getALlInheritedFeatures(Type type) {
        Set<Feature> inheritedFeatures = new HashSet<>();
        for (Type superType : type.allSupertypes()) {
            inheritedFeatures.addAll(TypeUtil.getPublicFeaturesOf(superType));
        }
        return inheritedFeatures;
    }

    public Set<Feature> getALlInheritedFeatures(Feature feature) {
        Type owningType = feature.getOwningType();
        if (owningType == null) {
            return Collections.emptySet();
        }
        return getALlInheritedFeatures(owningType);
    }

    public Optional<Type> getOwingType(Feature feature) {
        return Optional.ofNullable(feature.getOwningType());
    }

    public <U extends Type, C extends Type, S extends Specialization, T extends SpecialicationGraph<U, C, S>> T getSpecialicationGraph(Class<T> graphClass) {
        try {
            return graphClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create specialization graph", e);
        }
    }

    public HashMap<Type, ElemWithMult> getAllMultplicities() {
        Set<Type> allTypes = this.collect(Type.class);
        HashMap<Type, ElemWithMult> result = new HashMap<>();
        for (Type t : allTypes) {
            MultiplicityRange mult = FeatureUtil.getMultiplicityRangeOf(t.getMultiplicity());
            if (mult != null) {
                int lower = mult.valueOf(mult.getLowerBound());
                int upper = mult.valueOf(mult.getUpperBound());
                if (lower < 0) lower = upper;

                result.put(t, (new ElemWithMult(lower, upper)));
            }
            else{
                result.put(t, (new ElemWithMult(1, 1)));
            }
        }
        return result;
    }

}