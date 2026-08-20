package org.example.Mapping.Mapper.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Calculation;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.FeatureReference;
import org.example.Mapping.Interfaces.Function;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.AbstractMappedQuery;
import org.example.Mapping.NewVersion.FunctionMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.InvocationExpression;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinCalculationExpression extends TwinExpression<InvocationExpression> implements Calculation {
	private MappedReference<? extends Function> calledFunction;
	private List<TwinExpression<?>> arguments = new ArrayList<>();

	public TwinCalculationExpression(InvocationExpression sysmlElement) {
		super(sysmlElement);

	}

	@Override
	public MappedReference<? extends Function> getCalledFunction() {
		return calledFunction;
	}

	@Override
	public List<Expression> getArguments() {
		return new ArrayList<Expression>(arguments);
	}

	@Override
	public String getName() {
		return getSysmlElement().getFunction().getName();
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		var function = getSysmlElement().getFunction();
		calledFunction = context.mapReference(function, FunctionMapped.class);
		for (var arg : getSysmlElement().getArgument()) {
			arguments.add(context.map(arg, this, TwinExpression.class));
		}

		if (calledFunction.getReferent() instanceof AbstractMappedQuery query) {
			validateQueryExpressions(query);
		}
	}

	private void validateQueryExpressions(AbstractMappedQuery query) throws MappingException {

		if (arguments.isEmpty()) {
			throw new MappingException("Query '%s' requires a twinAttribute argument.".formatted(query.getName()));
		}
		for (TwinExpression<?> argument : arguments) {
			System.out.println(argument);
		}

		TwinExpression<?> twinAttributeArgument = arguments.getFirst();

		if (!(twinAttributeArgument instanceof FeatureReference)) {
			throw new MappingException("Query '%s': twinAttribute must be a FeatureReference.".formatted(query.getName()));
		}
	}
}