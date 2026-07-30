package org.example.GenerelRules;


import org.example.UtilClasses.RedefinitionGraph;
import org.example.UtilClasses.SubsettingGraph;
import org.example.Utils;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Inherited;
import java.util.*;

public class RedefintionRules extends SpecialicingRules {

    private static final Logger log = LoggerFactory.getLogger(RedefintionRules.class);

    @Override
    public boolean isValid() {
        Element rootElement = getUtils().getRootElement();
        boolean inheritedValid = checkOnlyInheritedRedefinition(rootElement);
        boolean onceValid = checkRedefinedOnce(rootElement);
        return inheritedValid && onceValid;
    }

    public boolean checkOnlyInheritedRedefinition(Element rootElement) {
        RedefinitionGraph subsettingGraph = getUtils().getSpecialicationGraph(RedefinitionGraph.class);
        boolean hasErrors = false;

        for (Map.Entry<Feature, Set<Feature>> entry : subsettingGraph.getForward().entrySet()) {
            Feature redefiningFeature = entry.getKey();
            Set<Feature> redefinedFeatures = entry.getValue();

            if (Utils.getInstance().isFromLibrary(redefiningFeature)) {
                continue;
            }

            Set<Feature> inheritedFeatures = getUtils().getALlInheritedFeatures(redefiningFeature);
            for (Feature redefinedFeature : redefinedFeatures) {
                if (!inheritedFeatures.contains(redefinedFeature)) {
                    logSpecialicingBut(redefiningFeature, redefinedFeature, "redefines", "but it is not inherited from the parent type.");
                    hasErrors = true;
                }
            }
        }

        return !hasErrors;
    }

    boolean checkRedefinedOnce(Element rootElement) {
        var redefinitionGraph = getUtils().getSpecialicationGraph(RedefinitionGraph.class);
        boolean hasErrors = false;

        Map<Feature, Set<Feature>> redefinedToRedefiningMap = redefinitionGraph.getBackward();
        for (Map.Entry<Feature, Set<Feature>> entry : redefinedToRedefiningMap.entrySet()) {
            Feature redefinedFeature = entry.getKey();
            Set<Feature> redefiningFeatures = entry.getValue();
            if (redefiningFeatures == null) continue;

            Map<Type, Set<Feature>> groupedByContext = new HashMap<>();
            for (Feature f : redefiningFeatures) {
                Type context = getUtils().getOwingType(f).orElse(null);
                groupedByContext.computeIfAbsent(context, k -> new HashSet<>()).add(f);
            }

            for (Set<Feature> contextGroup : groupedByContext.values()) {
                if (contextGroup.size() > 1) {
                    logSpecialicingBut(redefinedFeature, contextGroup, "is redefined from ", "but we dont allow multiple redefinitons of the same feature.");
                    hasErrors = true;
                }
            }
        }

        return !hasErrors;
    }
}