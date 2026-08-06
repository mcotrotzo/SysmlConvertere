package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Assignment;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttributeMapped;
import org.omg.sysml.lang.sysml.AssignmentActionUsage;

@MappedMetaclass
@ToString
public class TwinAssignmentActionMapped extends TwinActionBaseUsage<AssignmentActionUsage> implements Assignment {
	private MappedReference<TwinAttributeMapped> referent;
	private TwinExpression<?> value;

	public TwinAssignmentActionMapped(AssignmentActionUsage sysmlElement) {
		super(sysmlElement);
	}

	public MappedReference<? extends TwinAttribute> getTarget() {
		return referent;
	}

	@Override
	public Expression getValue() {
		return value;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		referent = context.mapReference(getSysmlElement().getReferent(), TwinAttributeMapped.class);
		value = context.map(this.sysmlElement.getValueExpression(), this, TwinExpression.class);
	}
}
