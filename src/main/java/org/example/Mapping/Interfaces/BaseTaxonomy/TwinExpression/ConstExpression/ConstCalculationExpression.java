package org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.Calculation;

/** A constant calculation may itself only receive constant expressions. */
public interface ConstCalculationExpression<E extends ConstExpression> extends Calculation<E>, ConstExpression {
}
