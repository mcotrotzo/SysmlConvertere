package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.IfElse;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.omg.sysml.lang.sysml.IfActionUsage;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinIfElseActionMapped extends TwinActionMapped<IfActionUsage, Usage> implements IfElse {
	private TwinExpression<?> ifExpression;
	private TwinActionMapped<?, Usage> thenAction;
	private TwinActionMapped<?, Usage> elseAction;

	public TwinIfElseActionMapped(IfActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public org.example.Mapping.Interfaces.TwinExpression.TwinExpression getCondition() {
		return ifExpression;
	}

	@Override
	public Action<Usage> getThenAction() {
		return thenAction;
	}

	@Override
	public Action<Usage> getElseAction() {
		return elseAction;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		ifExpression = context.map(this.getSysmlElement().getIfArgument(), this, TwinExpression.class);
		thenAction = context.map(this.getSysmlElement().getThenAction(), this, TwinActionMapped.getActionMappedUsageClass());
		if (this.getSysmlElement().getElseAction() != null) {
			elseAction = context.map(this.getSysmlElement().getElseAction(), this, TwinActionMapped.getActionMappedUsageClass());

		}
	}
}
