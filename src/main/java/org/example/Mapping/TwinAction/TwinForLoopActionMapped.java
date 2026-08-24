package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.ForLoop;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.ForLoopActionUsage;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinForLoopActionMapped extends TwinActionUsageMapped<ForLoopActionUsage> implements ForLoop {
	private TwinAttributeUsageMapped loopVariable;
	private TwinExpression<?> expr;
	private TwinActionUsageMapped<?> body;

	public TwinForLoopActionMapped(ForLoopActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public TwinAttributeUsage getLoopVariable() {
		return loopVariable;
	}

	@Override
	public org.example.Mapping.Interfaces.TwinExpression.TwinExpression getCollection() {
		return expr;
	}

	@Override
	public Action getBody() {
		return body;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		loopVariable = context.mapAttribute(
				getSysmlElement().getLoopVariable(),
				this,TwinAttributeUsageMapped.class, Role.FOR_LOOP_VARIABLE
		);
		expr = context.map(
				getSysmlElement().getSeqArgument(),
				this,
				TwinExpression.class
		);

		body = context.map(
				getSysmlElement().getBodyAction(),
				this,
				TwinActionUsageMapped.class
		);
	}
}
