package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.IfElse;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.omg.sysml.lang.sysml.IfActionUsage;

import java.util.Optional;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinIfElseActionMapped
		extends TwinActionMapped<IfActionUsage, Usage>
		implements IfElse {

	private TwinExpression<?> ifExpression;

	private CompartmentMapped<TwinActionMapped<?, Usage>> thenAction;

	private Optional<CompartmentMapped<TwinActionMapped<?, Usage>>> elseAction = Optional.empty();


	public TwinIfElseActionMapped(IfActionUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public org.example.Mapping.Interfaces.TwinExpression.TwinExpression getCondition() {
		return ifExpression;
	}

	@Override
	public CompartmentMapped<TwinActionMapped<?, Usage>> getThenAction() {
		return thenAction;
	}

	@Override
	public Optional<? extends CompartmentMapped<TwinActionMapped<?, Usage>>> getElseAction() {
		return elseAction;
	}


	@Override
	public void parse(MappingContext context) throws MappingException {

		ifExpression = context.map(
				getSysmlElement().getIfArgument(),
				this,
				TwinExpression.class
		);

		TwinActionMapped<?, Usage> mappedThenAction = context.map(
				getSysmlElement().getThenAction(),
				this,
				TwinActionMapped.getActionMappedUsageClass()
		);

		thenAction = new CompartmentMapped<>(
				mappedThenAction,
				mappedThenAction.getOwner() != this
		);

		if (getSysmlElement().getElseAction() != null) {

			TwinActionMapped<?, Usage> mappedElseAction = context.map(
					getSysmlElement().getElseAction(),
					this,
					TwinActionMapped.getActionMappedUsageClass()
			);

			elseAction = Optional.of(new CompartmentMapped<>(
					mappedElseAction,
					mappedElseAction.getOwner() != this
			));
		}
	}
}