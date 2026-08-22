package org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstConstructorExpression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinConstructorExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.ConstructorExpression;

@MappedMetaclass
public class ConstConstructorExpressionMapped<T extends ConstExpressionMapped<?>>
        extends TwinConstructorExpression<T>
        implements ConstConstructorExpression<T> {
    public ConstConstructorExpressionMapped(ConstructorExpression sysmlElement) {
        super(sysmlElement);
    }
}
