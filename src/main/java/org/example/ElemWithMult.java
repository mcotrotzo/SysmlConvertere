package org.example;

import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.util.FeatureUtil;

import java.lang.reflect.Type;
import java.util.List;

public class ElemWithMult {
    private final int lowerBound;
    private final int upperBound; // -1 = *

    public ElemWithMult(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getLowerBound() { return lowerBound; }
    public int getUpperBound() { return upperBound; }

    @Override
    public String toString() {
        return "["+lowerBound+" .. " +upperBound+"]";
    }
}
