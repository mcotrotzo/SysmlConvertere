package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttributeLoopVariableMapped;
import org.example.Mapping.NewVersion.TwinAttributeMapped;
import org.omg.sysml.lang.sysml.ForLoopActionUsage;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinForLoopActionMapped extends TwinActionBaseUsage<ForLoopActionUsage> implements ForLoop {
	private TwinAttributeLoopVariableMapped loopVariable;
	private TwinExpression<?> expr;
	private TwinActionBaseUsage<?> body;

	public TwinForLoopActionMapped(ForLoopActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public TwinAttribute getLoopVariable() {
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

		loopVariable = context.mapLoopVariable(
				getSysmlElement().getLoopVariable(),
				this
		);

		expr = context.map(
				getSysmlElement().getSeqArgument(),
				this,
				TwinExpression.class
		);

		body = context.map(
				getSysmlElement().getBodyAction(),
				this,
				TwinActionBaseUsage.class
		);
	}
}
