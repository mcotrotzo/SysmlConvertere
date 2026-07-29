package org.example.UtilClasses;

import org.example.Utils;
import org.omg.sysml.lang.sysml.Feature;

import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Redefinition;
import org.omg.sysml.util.FeatureUtil;

import java.util.*;

public class RedefinitionGraph extends SpecialicationGraph<Feature,Feature,Redefinition> {


    public RedefinitionGraph( Element rootElement) {
        super(Feature.class, Feature.class, Redefinition.class, rootElement);
    }


}