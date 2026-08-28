package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.WhileLoop;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.omg.sysml.lang.sysml.WhileLoopActionUsage;

import java.util.Optional;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinWhileActionMapped
		extends TwinActionMapped<WhileLoopActionUsage, Usage>
		implements WhileLoop {

	private TwinExpression<?> condition;
	private TwinExpression<?> until;

	private Optional<CompartmentMapped<TwinActionMapped<?, Usage>>> body =
			Optional.empty();

	public TwinWhileActionMapped(WhileLoopActionUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		if (getSysmlElement().getWhileArgument() != null) {
			condition = context.map(
					getSysmlElement().getWhileArgument(),
					this,
					TwinExpression.class
			);
		}

		if (getSysmlElement().getUntilArgument() != null) {
			until = context.map(
					getSysmlElement().getUntilArgument(),
					this,
					TwinExpression.class
			);
		}

		if (getSysmlElement().getBodyAction() != null) {
			var bodyAction = getSysmlElement().getBodyAction();

			TwinActionMapped<?, Usage> mappedBody = context.map(
					bodyAction,
					this,
					TwinActionMapped.getActionMappedUsageClass()
			);

			body = Optional.of(
					new CompartmentMapped<>(
							mappedBody,
							bodyAction.getOwner() != getSysmlElement()
					)
			);
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
	public Optional<CompartmentMapped<TwinActionMapped<?, Usage>>> getBody() {
		return body;
	}
}