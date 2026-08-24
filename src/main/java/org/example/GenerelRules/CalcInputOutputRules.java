package org.example.GenerelRules;


import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.*;

import java.util.List;

public class CalcInputOutputRules extends GenerelRules {

	public CalcInputOutputRules(Utils utils) {
		super(utils);
	}

	@Override
	public boolean isValid() throws MappingException {

		checkOnlyRedefinitions();

		return true;
	}

	private void checkOnlyRedefinitions() throws MappingException {

		for (ActionDefinition calcDef : this.utilsManager.collect(ActionDefinition.class)) {

			checkCalculation(calcDef);
		}

		for (ActionUsage calcDef : this.utilsManager.collect(ActionUsage.class)) {

			checkCalculation(calcDef);
		}
	}


	private void checkCalculation(Type calcDef) throws MappingException {

		for (Feature feature : calcDef.getOwnedFeature()) {

			if (!isParameter(feature)) {
				continue;
			}

			boolean hasPlainSubsetting = feature.getOwnedSubsetting().stream().anyMatch(subsetting -> !(subsetting instanceof Redefinition));

			if (hasPlainSubsetting) {
				throw new MappingException(("Calculation '%s': parameter '%s' may only specialize " + "another in calculation parameter by redefinition.").formatted(calcDef.getQualifiedName(), feature.getName()));
			}
		}
	}



	private boolean isParameter(Feature feature) {

		return feature.getDirection() != null;
	}

}