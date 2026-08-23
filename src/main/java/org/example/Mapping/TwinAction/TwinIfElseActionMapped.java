package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.IfElse;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.IfActionUsage;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinIfElseActionMapped extends TwinActionUsageMapped<IfActionUsage> implements IfElse {
	private TwinExpression<?> ifExpression;
	private TwinActionUsageMapped<?> thenAction;
	private TwinActionUsageMapped<?> elseAction;

	public TwinIfElseActionMapped(IfActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public org.example.Mapping.Interfaces.TwinExpression.TwinExpression getCondition() {
		return ifExpression;
	}

	@Override
	public Action getThenAction() {
		return thenAction;
	}

	@Override
	public Action getElseAction() {
		return elseAction;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		ifExpression = context.map(this.getSysmlElement().getIfArgument(), this, TwinExpression.class);
		thenAction = context.map(this.getSysmlElement().getThenAction(), this, TwinActionUsageMapped.class);
		elseAction = context.map(this.getSysmlElement().getElseAction(), this, TwinActionUsageMapped.class);
	}
}
