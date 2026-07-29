package org.example.UtilClasses;

import jakarta.inject.Inject;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.example.Utils;
import org.omg.sysml.lang.sysml.*;

import java.lang.Class;
import java.util.*;

public abstract class SpecialicationGraph<T extends Type, C extends Type, S extends Specialization> {

    private final Class<T> specificClass;
    private final Class<C> generalClass;
    private final Class<S> specializationClass;

    private Map<T, Set<C>> forward = new HashMap<>();
    private Map<C, Set<T>> backward = new HashMap<>();


    private final Utils utils = Utils.getInstance();

    public Element getRootElement() {
        return utils.getRootElement();
    }

    public Utils getUtils() {
        return utils;
    }

    public SpecialicationGraph(Class<T> specificClass, Class<C> generalClass, Class<S> specializationClass, Element rootElement) {
        this.specificClass = specificClass;
        this.generalClass = generalClass;
        this.specializationClass = specializationClass;
        computeMaps();
    }

    public Set<C> getSpecializedBy(T redefining) {
        return forward.getOrDefault(redefining, Collections.emptySet());
    }

    public Set<T> getSpecificationsOf(C redefined) {
        return backward.getOrDefault(redefined, Collections.emptySet());
    }

    public Set<T> getAllSpecialized() {
        return Collections.unmodifiableSet(forward.keySet());
    }

    public Set<C> getAllSpecifications() {
        return Collections.unmodifiableSet(backward.keySet());
    }

    public void computeMaps() {
        Set<S> allSpecializations = utils.collect(specializationClass);

        for (Specialization spec : allSpecializations) {
            Type general = utils.convertBasicFeatureToType(spec.getGeneral());
            Type specific = utils.convertBasicFeatureToType(spec.getSpecific());

            if (general.equals(specific)) {
                continue;
            }

            if (!specificClass.isInstance(specific) || !generalClass.isInstance(general)) {
                continue;
            }

            addSpecialization(specificClass.cast(specific), generalClass.cast(general));
        }
    }

    private void addSpecialization(T specialization, C general) {
        forward.computeIfAbsent(specialization, k -> new HashSet<>()).add(general);
        backward.computeIfAbsent(general, k -> new HashSet<>()).add(specialization);
    }

    public Map<T, Set<C>> getForward() {
        return forward;
    }

    public Map<C, Set<T>> getBackward() {
        return backward;
    }
}