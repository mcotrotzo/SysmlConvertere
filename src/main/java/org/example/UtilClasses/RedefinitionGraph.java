package org.example.UtilClasses;

import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Redefinition;

public class RedefinitionGraph extends SpecialicationGraph<Feature, Feature, Redefinition> {


	public RedefinitionGraph(Utils utils) {
		super(utils, Feature.class, Feature.class, Redefinition.class);
	}


}