package org.example.UtilClasses;

import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Subsetting;

public class SubsettingGraph extends SpecialicationGraph<Feature,Feature,Subsetting> {

    public SubsettingGraph() {
        super(Feature.class, Feature.class, Subsetting.class);
    }
}