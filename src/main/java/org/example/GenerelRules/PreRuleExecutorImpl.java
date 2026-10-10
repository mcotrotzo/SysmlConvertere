package org.example.GenerelRules;


import Executor.Executor;
import Executor.GenerelRules;
import Executor.SemanticRule;
import Mapper.NewUtil;
import Rules.MultiType;
import Rules.MultiplicityRule;
import org.example.SemanticRules.*;
import java.util.List;

public class PreRuleExecutorImpl extends Executor {

	@Override
	public List<GenerelRules> getGeneralRules(NewUtil newUtil) {
		return List.of(new CalcInputOutputRules(newUtil),
				new MultiplicityRule(newUtil),
				new MultiType(newUtil),
				new TwinAttributeHasToSpecialiced(newUtil)
				);
	}

	@Override
	public List<SemanticRule> getSemanticRules(NewUtil newUtil) {
		return List.of(new CheckAssignemntRules(newUtil),
				new FlowRules(newUtil),
				new TwinBoundaryRules(newUtil)
				);
	}
}
