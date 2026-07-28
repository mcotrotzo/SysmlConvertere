package org.example;

import org.eclipse.xtext.validation.Issue;
import org.omg.sysml.interactive.SysMLInteractiveResult;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SysmlInteractiveExtension extends SysMLInteractiveResult {

    private List<ElemWithMult> elementsWithMulti = new ArrayList<>();

    public SysmlInteractiveExtension(Element rootElement, List<Issue> issues) {
        super(rootElement, issues);
    }

    public SysmlInteractiveExtension(Exception exception) {
        super(exception);
    }


    public void checkMultiplicty() {
        for (Element element : getRootElement().getOwnedElement()) {
            checkMultiplicty(element);
        }

        for (ElemWithMult element : this.elementsWithMulti) {
            validate(element);
        }
    }
    private List<Feature> findAllSubsettersOf(Feature target, Element ns) {
        List<Feature> result = new ArrayList<>();
        for (Element el : ns.getOwnedElement()) {
            if (el instanceof Feature f && FeatureUtil.getSubsettedFeaturesOf(f).contains(target)) {
                result.add(f);
            }
            result.addAll(findAllSubsettersOf(target, el));
        }
        return result;
    }

    private void validate(ElemWithMult elemWithMult) {
        if (!(elemWithMult.getElement() instanceof Feature targetFeature)) return;

        List<Feature> subsetters = findAllSubsettersOf(targetFeature, getRootElement());
        int upper = elemWithMult.getUpperBound();

        if (upper == -1) return; // kein Limit

        int totalSubsetterUpper = 0;
        boolean anyUnbounded = false;

        for (Feature s : subsetters) {
            if (!(s instanceof Type st)) continue;
            MultiplicityRange subMult = FeatureUtil.getMultiplicityRangeOf(st.getMultiplicity());

            int subUpper;
            if (subMult != null) {
                subUpper = subMult.valueOf(subMult.getUpperBound());
            } else {
                subUpper = 1;
            }

            if (subUpper == -1) {
                anyUnbounded = true;
            } else {
                totalSubsetterUpper += subUpper;
            }
        }

        if (anyUnbounded || totalSubsetterUpper > upper) {
            System.err.println("ERROR: " + targetFeature.getName() +
                    " hat Limit [" + upper + "], aber Summe der Subsetter-Multiplizitäten ist " +
                    (anyUnbounded ? "unbeschränkt (*)" : totalSubsetterUpper));
            subsetters.forEach(s -> System.err.println("  - " + s.getName()));
        }
    }

    public void checkMultiplicty(Element element) {
        for (Element el : element.getOwnedElement()) {
            if (el.getName() == null || !(el instanceof Type)) continue;

            MultiplicityRange mult = FeatureUtil.getMultiplicityRangeOf(((Type) el).getMultiplicity());
            if (mult != null) {
                int lower = mult.valueOf(mult.getLowerBound());
                int upper = mult.valueOf(mult.getUpperBound());

                elementsWithMulti.add(new ElemWithMult(el, lower, upper));
            }
            checkMultiplicty(el);
        }
    }
}

