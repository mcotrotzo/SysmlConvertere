package org.example.Mapping.Mapper.TwinAttributeMapped.AttributeWithExpressionMapped;

import lombok.Getter;
import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.AttributeWithExpression.TwinAttributeWithExpression;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinExpression;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;

import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;

import java.util.List;

@ToString(callSuper = true)
public abstract class TwinAttributeWithExpressionMapped<E extends TwinExpression<?>>
        extends TwinAttributeUsageMapped {

    private E expression;

    public TwinAttributeWithExpressionMapped(Feature sysmlElement) {
        super(sysmlElement);
    }

	public E getExpression() {
		return expression;
	}
	@Override
    public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		//TODO
	}
}
