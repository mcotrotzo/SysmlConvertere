package org.example.UtilClasses;

import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Subsetting;

public class SubsettingGraph extends SpecialicationGraph<Feature, Feature, Subsetting> {

	public SubsettingGraph(Utils utils) {
		super(utils, Feature.class, Feature.class, Subsetting.class);
	}
}