package org.example;

import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.util.FeatureUtil;

import java.util.List;

public class ElemWithMult {
    private final Element element;
    private final int lowerBound;
    private final int upperBound; // -1 = *

    public ElemWithMult(Element element, int lowerBound, int upperBound) {
        this.element = element;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Element getElement() { return element; }
    public int getLowerBound() { return lowerBound; }
    public int getUpperBound() { return upperBound; }
}
