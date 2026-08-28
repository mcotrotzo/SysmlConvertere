package org.example.Mapping.TwinAction;

import lombok.ToString;
import org.example.Mapping.AdditionalRoles;
import org.example.Mapping.Interfaces.Base.TypeKind.RoleClass;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.ForLoop;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.omg.sysml.lang.sysml.ForLoopActionUsage;

import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinForLoopActionMapped
		extends TwinActionMapped<ForLoopActionUsage, Usage>
		implements ForLoop {

	private CompartmentMapped<TwinAttributeMapped<Usage>> loopVariable;
	private TwinExpression<?> expr;
	private CompartmentMapped<TwinActionMapped<?, Usage>> body;

	public TwinForLoopActionMapped(ForLoopActionUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public CompartmentMapped<TwinAttributeMapped<Usage>> getLoopVariable() {
		return loopVariable;
	}

	@Override
	public org.example.Mapping.Interfaces.TwinExpression.TwinExpression getCollection() {
		return expr;
	}

	@Override
	public CompartmentMapped<TwinActionMapped<?, Usage>> getBody() {
		return body;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		TwinAttributeMapped<Usage> mappedLoopVariable = context.map(
				getSysmlElement().getLoopVariable(),
				this,
				TwinAttributeMapped.getRawUsageClass()
		);

		loopVariable = new CompartmentMapped<>(
				mappedLoopVariable,
				mappedLoopVariable.getOwner() != this
		);

		expr = context.map(
				getSysmlElement().getSeqArgument(),
				this,
				TwinExpression.class
		);

		TwinActionMapped<?, Usage> mappedBody = context.map(
				getSysmlElement().getBodyAction(),
				this,
				TwinActionMapped.getActionMappedUsageClass()
		);

		body = new CompartmentMapped<>(
				mappedBody,
				mappedBody.getOwner() != this
		);
	}

	@Override
	protected List<AdditionalRoles> addAdditionalRoles() {
		return List.of(
				new AdditionalRoles(
						List.of(loopVariable.getElement()),
						List.of(
								new RoleClass(
										TwinForLoopActionMapped.class,
										Role.FOR_LOOP_VARIABLE
								)
						)
				)
		);
	}
}