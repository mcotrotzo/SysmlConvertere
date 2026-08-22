package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Assignment;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.omg.sysml.lang.sysml.AssignmentActionUsage;

@MappedMetaclass
@ToString
public class TwinAssignmentActionMapped extends TwinActionBaseUsage<AssignmentActionUsage> implements Assignment {
	private MappedReference<TwinAttributeUsageMapped> referent;
	private TwinExpression<?> value;

	public TwinAssignmentActionMapped(AssignmentActionUsage sysmlElement) {
		super(sysmlElement);
	}

	public Reference<? extends TwinAttributeUsage> getTarget() {
		return referent;
	}

	@Override
	public Expression getValue() {
		return value;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		referent = context.mapReference(getSysmlElement().getReferent(), TwinAttributeUsageMapped.class);
		value = context.map(this.sysmlElement.getValueExpression(), this, TwinExpression.class);
	}
}
