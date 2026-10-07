package org.example.SemanticRules;

import org.example.Mapping.Model.AbstractModel;
import org.example.TwinDataBase;
import org.example.Util.NewUtil;
import org.omg.sysml.lang.sysml.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckUnMappedElements extends SemanticRule {
	public CheckUnMappedElements(NewUtil newUtil) {
		super(newUtil);
	}

	@Override
	public boolean isValid(TwinDataBase database) throws SemanticException {
		Set<Element> mapped = database.getAll().stream().map(AbstractModel::getSysmlElement).collect(Collectors.toSet());

		for (Type type : newUtil.getUserRawType().getAllElements()) {
			if (isModeledByUser(type) && !mapped.contains(type)) {
				throw new SemanticException("Element " + type.getName() + " with path " + type.path() + " is not mapped in the database. It is placed wrong");
			}
		}
		return true;
	}


	private boolean isModeledByUser(Type type) {
		if (!(type instanceof Usage || type instanceof Definition)) return false;
		if (type instanceof Expression || type instanceof ReferenceUsage) return false;
		if (type instanceof ConjugatedPortDefinition) return false;
		if (type instanceof SuccessionAsUsage && type.getOwner() instanceof TransitionUsage) return false;
		Membership membership = type.getOwningMembership();
		if (membership != null && membership.isImplied()) return false;
		for (Element owner = type.getOwner(); owner != null; owner = owner.getOwner()) {
			if (owner instanceof Expression) return false;
		}
		return true;
	}
}
