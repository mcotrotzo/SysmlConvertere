package org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstructorCall;

/** A constant constructor may itself only receive constant expressions. */
public interface ConstConstructorExpression extends ConstructorCall<ConstExpression>, ConstExpression {
}
