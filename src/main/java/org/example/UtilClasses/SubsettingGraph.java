package org.example.UtilClasses;

import org.example.Utils;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Redefinition;
import org.omg.sysml.lang.sysml.Subsetting;
import org.omg.sysml.util.FeatureUtil;

import java.util.*;

public class SubsettingGraph extends SpecialicationGraph<Feature,Feature,Subsetting> {

    public SubsettingGraph(Element rootElement) {
        super(Feature.class, Feature.class, Subsetting.class, rootElement);
    }
}