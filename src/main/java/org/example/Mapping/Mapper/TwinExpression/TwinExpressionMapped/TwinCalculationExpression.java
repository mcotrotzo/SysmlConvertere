package org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.Function;
import org.example.Mapping.Interfaces.Reference;
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
public class TwinCalculationExpression<T extends TwinExpression<?>> extends TwinInvocationExpression<T,InvocationExpression,FunctionMapped<?>> implements Calculation<T> {
	private MappedReference<? extends Function> calledFunction;
	public TwinCalculationExpression(InvocationExpression sysmlElement) {
		super(sysmlElement);

	}
	@Override
	public Reference<? extends Function> getCalledFunction() {
		return getInvokeType();
	}
}