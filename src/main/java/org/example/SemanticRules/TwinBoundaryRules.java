package org.example.SemanticRules;



import Executor.SemanticException;
import Executor.SemanticRule;
import Main.ResultConverter;
import Mapper.NewUtil;
import Model.AbstractType;
import org.example.Mapping.Model.Action.TwinAssignmentUsage;
import Model.Predefined.MetaClasses.Expression.ReferenceUsage;
import org.example.Mapping.Model.Flow.FederationFlowUsage;
import org.example.Mapping.Model.Flow.TwinFlowUsage;
import org.example.Mapping.Model.Twin.TwinCoreApi;
import java.util.Optional;

public class TwinBoundaryRules extends SemanticRule {


	public TwinBoundaryRules(NewUtil newUtil) {
		super(newUtil);
	}

	@Override
	public boolean isValid(ResultConverter resultConverter) throws SemanticException {
		for (ReferenceUsage<?> reference : resultConverter.getByType(ReferenceUsage.class )) {
			if (!reference.isLibrary()) {
				check(reference, reference.getTarget(), "Reference to");
			}
		}
		for (TwinAssignmentUsage assignment : resultConverter.getByType(TwinAssignmentUsage.class)) {
			if (!assignment.isLibrary()) {
				check(assignment, assignment.getReferent(), "Assignment to");
			}
		}
		for (TwinFlowUsage flow : resultConverter.getByType(TwinFlowUsage.class)) {
			if (flow.isLibrary() || flow instanceof FederationFlowUsage) {
				continue;
			}
			check(flow, flow.getSource(), "Flow source");
			check(flow, flow.getTarget(), "Flow target");
		}
		return true;
	}

	private void check(AbstractType<?, ?> user, AbstractType<?, ?> used, String what) throws SemanticException {
		Optional<AbstractType<?, ?>> userTwin = twinOf(user);
		Optional<AbstractType<?, ?>> usedTwin = twinOf(used);
		if (!userTwin.map(AbstractType::getId).equals(usedTwin.map(AbstractType::getId))) {
			throw new SemanticException("%s '%s' leaves its twin: it is used in '%s' but belongs to '%s'. Use a federation flow to connect twins."
					.formatted(what, used.getName(), userTwin.map(AbstractType::getName).orElse("no twin"), usedTwin.map(AbstractType::getName).orElse("no twin")));
		}
	}


	private Optional<AbstractType<?, ?>> twinOf(AbstractType<?, ?> element) {
		for (AbstractType<?, ?> current = element; current != null; current = current.getParent().orElse(null)) {
			if (current instanceof TwinCoreApi) {
				return Optional.of(current);
			}
		}
		return Optional.empty();
	}
}
