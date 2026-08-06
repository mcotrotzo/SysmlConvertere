package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.WhileLoop;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.WhileLoopActionUsage;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinWhileActionMapped extends TwinActionBaseUsage<WhileLoopActionUsage> implements WhileLoop {

	private TwinExpression<?> condition;
	private TwinExpression<?> until;
	private TwinActionBaseUsage<?> body;

	public TwinWhileActionMapped(WhileLoopActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		if (sysmlElement.getWhileArgument() != null) {
			condition = context.map(this.getSysmlElement().getWhileArgument(), this, TwinExpression.class);
		}
		if (sysmlElement.getUntilArgument() != null) {
			until = context.map(this.getSysmlElement().getUntilArgument(), this, TwinExpression.class);
		}
		if (sysmlElement.getBodyAction() != null) {
			body = context.map(this.getSysmlElement().getBodyAction(), this, TwinActionBaseUsage.class);
		}
	}

	@Override
	public Expression getCondition() {
		return condition;
	}

	@Override
	public Expression getUntil() {
		return until;
	}

	@Override
	public Action getBody() {
		return (Action) body;
	}
}
