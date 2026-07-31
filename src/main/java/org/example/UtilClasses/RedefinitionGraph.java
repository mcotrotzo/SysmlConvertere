package org.example.UtilClasses;

import org.omg.sysml.lang.sysml.Feature;

import org.omg.sysml.lang.sysml.Redefinition;

public class RedefinitionGraph extends SpecialicationGraph<Feature,Feature,Redefinition> {


    public RedefinitionGraph() {
        super(Feature.class, Feature.class, Redefinition.class);
    }


}