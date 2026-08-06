package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.IfElse;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.IfActionUsage;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinIfElseActionMapped extends TwinActionBaseUsage<IfActionUsage> implements IfElse {
	private TwinExpression<?> ifExpression;
	private TwinActionBaseUsage<?> thenAction;
	private TwinActionBaseUsage<?> elseAction;

	public TwinIfElseActionMapped(IfActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public Expression getCondition() {
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
		super.parse(context);
		ifExpression = context.map(this.getSysmlElement().getIfArgument(), this, TwinExpression.class);
		thenAction = context.map(this.getSysmlElement().getThenAction(), this, TwinActionBaseUsage.class);
		elseAction = context.map(this.getSysmlElement().getElseAction(), this, TwinActionBaseUsage.class);
	}
}
