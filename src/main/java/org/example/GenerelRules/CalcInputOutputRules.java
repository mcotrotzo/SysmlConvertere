package org.example.GenerelRules;




import Executor.GenerelRules;
import Mapper.NewUtil;
import org.omg.sysml.lang.sysml.*;

public class CalcInputOutputRules extends GenerelRules {

	public CalcInputOutputRules(NewUtil utils) {
		super(utils);
	}

	@Override
	public boolean isValid() throws IllegalArgumentException {

		checkOnlyRedefinitions();

		return true;
	}

	private void checkOnlyRedefinitions() throws IllegalArgumentException {

		for (ActionDefinition calcDef : this.utilsManager.collect(ActionDefinition.class)) {

			checkCalculation(calcDef);
		}

		for (ActionUsage calcDef : this.utilsManager.collect(ActionUsage.class)) {

			checkCalculation(calcDef);
		}
	}


	private void checkCalculation(Type calcDef) throws IllegalArgumentException {

		for (Feature feature : calcDef.getOwnedFeature()) {

			if (!isParameter(feature)) {
				continue;
			}

			boolean hasPlainSubsetting = feature.getOwnedSubsetting().stream().anyMatch(subsetting -> !(subsetting instanceof Redefinition));

			if (hasPlainSubsetting) {
				throw new IllegalArgumentException(("Calculation '%s': parameter '%s' may only specialize " + "another in calculation parameter by redefinition.").formatted(calcDef.getQualifiedName(), feature.getName()));
			}
		}
	}


	private boolean isParameter(Feature feature) {

		return feature.getDirection() != null;
	}

}