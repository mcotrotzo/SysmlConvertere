package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.ForLoop;
import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttributeMapped;
import org.omg.sysml.lang.sysml.ForLoopActionUsage;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinForLoopActionMapped extends TwinActionBaseUsage<ForLoopActionUsage> implements ForLoop {
	private TwinAttributeMapped loopVariable;
	private TwinExpression<?> expr;
	private TwinActionBaseUsage<?> body;

	public TwinForLoopActionMapped(ForLoopActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public Model getLoopVariable() {
		return loopVariable;
	}

	@Override
	public Expression getCollection() {
		return expr;
	}

	@Override
	public Action getBody() {
		return body;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		loopVariable = context.map(this.getSysmlElement().getLoopVariable(), this, TwinAttributeMapped.class);
		expr = context.map(this.getSysmlElement().getSeqArgument(), this, TwinExpression.class);
		body = context.map(this.getSysmlElement().getBodyAction(), this, TwinActionBaseUsage.class);
	}
}
