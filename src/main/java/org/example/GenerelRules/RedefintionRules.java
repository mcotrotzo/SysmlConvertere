package org.example.GenerelRules;

import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Redefinition;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

import java.util.*;

public class RedefintionRules implements GenerelRules {

    private final List<Feature> featuresList = new ArrayList<>();

    @Override
    public boolean isValid(Element rootElement) {
        flattenElements(rootElement);

        boolean hasErrors = false;

        for (Feature feature : featuresList) {
            // Nur Features prüfen, die explizit ein ':>>' deklariert haben
            List<Redefinition> ownedRedefinitions = feature.getOwnedRedefinition();
            if (ownedRedefinitions.isEmpty()) {
                continue;
            }

            Type ownerType = feature.getOwningType();
            if (ownerType == null) {
                continue;
            }

            // Alle geerbten Features aus dem gesamten umschließenden Kontext einsammeln
            Set<Feature> availableInheritedFeatures = getAllAvailableInheritedFeatures(ownerType);

            for (Redefinition redef : ownedRedefinitions) {
                Feature redefinedTarget = redef.getRedefinedFeature();
                Feature basicTarget = FeatureUtil.getBasicFeatureOf(redefinedTarget);

                if (basicTarget == null) continue;

                // Prüfen, ob das Ziel-Feature in den geerbten Features enthalten ist
                if (!availableInheritedFeatures.contains(basicTarget) && !availableInheritedFeatures.contains(redefinedTarget)) {

                    String featureName = FeatureUtil.computeEffectiveName(feature);
                    String targetName = FeatureUtil.computeEffectiveName(basicTarget);

                    String className = ownerType.getName() != null ? ownerType.getName() : "<anonymous>";

                    System.err.println("Error: Feature '" + featureName + "' in '" + className +
                            "' redefines '" + targetName + "' via (:>>), but it is NOT inherited!");

                    hasErrors = true;
                }
            }
        }

        return !hasErrors;
    }

    /**
     * Wandert den Erstellungskontext nach oben und sammelt alle geerbten Features
     * aus dem aktuellen Typ sowie allen umschließenden äußeren Typen ein.
     */
    private Set<Feature> getAllAvailableInheritedFeatures(Type currentType) {
        Set<Feature> inheritedFeatures = new HashSet<>();
        Set<Feature> availableInheritedFeatures = new HashSet<>();
        for (Type superType : currentType.allSupertypes()) {
            inheritedFeatures.addAll(TypeUtil.getPublicFeaturesOf(superType));
        }
        availableInheritedFeatures.addAll(currentType.getInheritedFeature());

        inheritedFeatures.forEach(x -> System.out.println(x.getName()));
        availableInheritedFeatures.forEach(x -> System.out.println(x.getName()));
        return inheritedFeatures;
    }

    private void flattenElements(Element element) {
        if (element instanceof Feature feature) {
            featuresList.add(feature);
        }
        for (Element child : element.getOwnedElement()) {
            flattenElements(child);
        }
    }
}