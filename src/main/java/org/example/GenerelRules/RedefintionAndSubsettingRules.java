package org.example.GenerelRules;

import org.example.ElemWithMult;
import org.example.UtilClasses.RedefinitionGraph;
import org.example.UtilClasses.SubsettingGraph;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RedefintionAndSubsettingRules extends SpecialicingRules {

    private static final Logger log = LoggerFactory.getLogger(RedefintionAndSubsettingRules.class);

    @Override
    public boolean isValid() {

        return checkMultplicitySpecialictions(this.getUtils().getRootElement());
    }

    private boolean checkMultplicitySpecialictions(Element rootElement) {
        boolean valid = true;
        Map<Type, ElemWithMult> allMultiplicities = getUtils().getAllMultplicities();

        Map<Feature, Set<Feature>> redefinitionsOfParent = getUtils().getSpecialicationGraph(RedefinitionGraph.class).getBackward();
        Map<Feature, Set<Feature>> subsetsOfParent = getUtils().getSpecialicationGraph(SubsettingGraph.class).getBackward();

        valid &= processSpecializationMap(redefinitionsOfParent, allMultiplicities, "redefines");

        valid &= processSpecializationMap(subsetsOfParent, allMultiplicities, "subsets");

        return valid;
    }

    private boolean processSpecializationMap(Map<Feature, Set<Feature>> parentMap, Map<Type, ElemWithMult> allMultiplicities, String specializeSymbol) {
        boolean valid = true;

        for (Feature specializedFeature : parentMap.keySet()) {
            ElemWithMult specializedMult = allMultiplicities.get(specializedFeature);
            if (specializedMult == null) {
                continue;
            }

            Set<Feature> specializingFeatures = parentMap.get(specializedFeature);
            if (specializingFeatures == null) {
                continue;
            }

            // Gruppieren nach Owning-Context, statt global zusammenzuwerfen
            Map<Type, Set<Feature>> groupedByContext = new HashMap<>();
            for (Feature f : specializingFeatures) {
                Type context = getUtils().getOwingType(f).orElse(null);
                groupedByContext.computeIfAbsent(context, k -> new HashSet<>()).add(f);
            }

            for (Set<Feature> contextGroup : groupedByContext.values()) {
                HashMap<Feature, ElemWithMult> specializingFeaturesWithMult = new HashMap<>();
                for (Feature child : contextGroup) {
                    ElemWithMult childMult = allMultiplicities.get(child);
                    if (childMult != null) {
                        specializingFeaturesWithMult.put(child, childMult);
                    }
                }
                valid &= checkMulti(specializedFeature, specializedMult, specializingFeaturesWithMult, specializeSymbol);
            }
        }

        return valid;
    }

    private boolean checkMulti(Feature specializedFeature, ElemWithMult specializedMult, HashMap<Feature, ElemWithMult> specializingFeaturesWithMult, String specializeSymbol) {

        int specializedLowerBound = specializedMult.getLowerBound();
        int specializedUpperBound = specializedMult.getUpperBound();

        int sumOfSpecializingLowerBounds = specializingFeaturesWithMult.values().stream()
                .filter(Objects::nonNull)
                .mapToInt(ElemWithMult::getLowerBound)
                .sum();

        if (specializedLowerBound > sumOfSpecializingLowerBounds) {
            String message = String.format("but the sum of lower bounds (%d) of the specializing features is less than the required lower bound (%d).",
                    sumOfSpecializingLowerBounds, specializedLowerBound);

            logSpecialicingBut(specializingFeaturesWithMult.keySet(), specializedFeature, specializeSymbol, message);
            return false;
        }

        if (specializedUpperBound == -1) {
            return true;
        }

        boolean hasUnboundedChild = specializingFeaturesWithMult.values().stream()
                .filter(Objects::nonNull)
                .anyMatch(m -> m.getUpperBound() == -1);

        int sumOfSpecializingUpperBounds = specializingFeaturesWithMult.values().stream()
                .filter(Objects::nonNull)
                .mapToInt(ElemWithMult::getUpperBound)
                .sum();

        if (hasUnboundedChild || sumOfSpecializingUpperBounds > specializedUpperBound) {
            String boundStr = hasUnboundedChild ? "unbounded (*)" : String.valueOf(sumOfSpecializingUpperBounds);
            String message = String.format("but the sum of upper bounds (%s) exceeds the allowed upper bound (%d).",
                    boundStr, specializedUpperBound);

            logSpecialicingBut(specializingFeaturesWithMult.keySet(), specializedFeature, specializeSymbol, message);
            return false;
        }

        return true;
    }
}