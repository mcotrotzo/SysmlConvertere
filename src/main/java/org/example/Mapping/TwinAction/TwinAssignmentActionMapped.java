package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Assignment;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.AssignmentActionUsage;

@MappedMetaclass
@ToString
public class TwinAssignmentActionMapped extends TwinActionMapped<AssignmentActionUsage, Usage> implements Assignment {
	private MappedReference<TwinAttributeMapped<Usage>> referent;
	private TwinExpression<?> value;

	public TwinAssignmentActionMapped(AssignmentActionUsage sysmlElement) {
		super(sysmlElement);
	}

	public Reference<? extends TwinAttribute<Usage>> getTarget() {
		return referent;
	}

	@Override
	public org.example.Mapping.Interfaces.TwinExpression.TwinExpression getValue() {
		return value;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		referent = context.mapReference(getSysmlElement().getReferent(), TwinAttributeMapped.getRawUsageClass());
		value = context.map(this.sysmlElement.getValueExpression(), this, TwinExpression.class);
	}

	@Override
	public void postValidate() throws MappingException {
		checkRole();
	}

	public void checkRole() throws MappingException {
		switch(referent.getReferent().getRole()){
			case LOCAL, ACTION, FOR_LOOP_VARIABLE -> {

			}
			default -> {
				throw new MappingException("Assignment target must be a local variable, action or for loop variable, but got: " + referent.getReferent().getRole());
			}
		}
	}
}
