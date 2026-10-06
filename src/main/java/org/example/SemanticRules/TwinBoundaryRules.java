package org.example.SemanticRules;


import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Action.TwinAssignmentUsage;
import org.example.Mapping.Model.Expression.TwinReferenceUsage;
import org.example.Mapping.Model.Flow.FederationFlowUsage;
import org.example.Mapping.Model.Flow.TwinFlowUsage;
import org.example.Mapping.Model.Slots;
import org.example.Mapping.Model.Twin.TwinCoreApi;
import org.example.TwinDataBase;

import java.util.Optional;

public class TwinBoundaryRules implements SemanticRule {

	private final Class<TwinReferenceUsage<?>> referenceClass = Slots.rawClassOf(TwinReferenceUsage.class);

	@Override
	public boolean isValid(TwinDataBase database) throws SemanticException {
		for (TwinReferenceUsage<?> reference : database.getByType(referenceClass)) {
			if (!reference.isLibrary()) {
				check(reference, reference.getTarget(), "Reference to");
			}
		}
		for (TwinAssignmentUsage assignment : database.getByType(TwinAssignmentUsage.class)) {
			if (!assignment.isLibrary()) {
				check(assignment, assignment.getReferent(), "Assignment to");
			}
		}
		for (TwinFlowUsage flow : database.getByType(TwinFlowUsage.class)) {
			if (flow.isLibrary() || flow instanceof FederationFlowUsage) {
				continue;
			}
			check(flow, flow.getSource(), "Flow source");
			check(flow, flow.getTarget(), "Flow target");
		}
		return true;
	}

	private void check(AbstractModel<?> user, AbstractModel<?> used, String what) throws SemanticException {
		Optional<AbstractModel<?>> userTwin = twinOf(user);
		Optional<AbstractModel<?>> usedTwin = twinOf(used);
		if (!userTwin.map(AbstractModel::getId).equals(usedTwin.map(AbstractModel::getId))) {
			throw new SemanticException("%s '%s' leaves its twin: it is used in '%s' but belongs to '%s'. Use a federation flow to connect twins."
					.formatted(what, used.getName(), userTwin.map(AbstractModel::getName).orElse("no twin"), usedTwin.map(AbstractModel::getName).orElse("no twin")));
		}
	}


	private Optional<AbstractModel<?>> twinOf(AbstractModel<?> element) {
		for (AbstractModel<?> current = element; current != null; current = current.getParent().orElse(null)) {
			if (current instanceof TwinCoreApi) {
				return Optional.of(current);
			}
		}
		return Optional.empty();
	}
}
