package org.example;

import jakarta.inject.Singleton;
import org.example.UtilClasses.SpecialicationGraph;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.lang.sysml.impl.FeatureImpl;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

import java.lang.Class;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;


public class Utils {

    private static Utils instance;

    private Utils() {

    }
    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }
    private Element rootElement;

    public Element getRootElement() {
        return rootElement;
    }

    public void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }

    public void walk(Consumer<Element> visitor) {
        walkInternal(this.rootElement, visitor);
    }

    private void walkInternal(Element current, Consumer<Element> visitor) {
        if (current == null) {
            return;
        }
        visitor.accept(current);
        for (Element child : current.getOwnedElement()) {
            walkInternal(child, visitor);
        }
        for (Relationship rel : current.getOwnedRelationship()) {
            if (!current.getOwnedElement().contains(rel)) {
                walkInternal(rel, visitor);
            }
        }
    }

    public <T extends Element> Set<T> collect(Class<T> type) {
        Set<T> result = new HashSet<>();
        walk(e -> {
            if (type.isInstance(e)) {
                result.add(type.cast(e));
            }
        });
        return result;
    }

    public <T extends Element> Set<T> collect(Class<T> type, Function<Element, Boolean> filter) {
        Set<T> result = new HashSet<>();
        walk(e -> {
            if (type.isInstance(e) && filter.apply(e)) {
                result.add(type.cast(e));
            }
        });
        return result;
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
            return graphClass.getConstructor(Element.class).newInstance(this.rootElement);
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
        }
        return result;
    }

    public boolean isDefinitionOrUsage(Element type) {
        return type instanceof Definition || type instanceof Usage;
    }

    public boolean isOwningType(Type type,Type isowningType) {
        if (type instanceof Feature) {
            return isowningType.equals(getOwingType((Feature) type).orElse(null));
        }
        return TypeUtil.specializes(type, isowningType);
    }

}