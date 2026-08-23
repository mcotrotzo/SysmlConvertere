package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.WhileLoop;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAction.Usage.TwinActionBlockUsage;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.WhileLoopActionUsage;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinWhileActionMapped extends TwinActionUsageMapped<WhileLoopActionUsage> implements WhileLoop {

	private TwinExpression<?> condition;
	private TwinExpression<?> until;
	private TwinActionBlockUsage<?> body;

	public TwinWhileActionMapped(WhileLoopActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		if (sysmlElement.getWhileArgument() != null) {
			condition = context.map(this.getSysmlElement().getWhileArgument(), this, TwinExpression.class);
		}
		if (sysmlElement.getUntilArgument() != null) {
			until = context.map(this.getSysmlElement().getUntilArgument(), this, TwinExpression.class);
		}
		if (sysmlElement.getBodyAction() != null) {
			body = context.map(this.getSysmlElement().getBodyAction(), this, TwinActionBlockUsage.class);
		}
	}

	@Override
	public org.example.Mapping.Interfaces.TwinExpression.TwinExpression getCondition() {
		return condition;
	}

	@Override
	public org.example.Mapping.Interfaces.TwinExpression.TwinExpression getUntil() {
		return until;
	}

	@Override
	public Action getBody() {
		return  body;
	}
}
