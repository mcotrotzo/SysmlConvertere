package org.example.Mapping.TwinExpression.TwinLiteralExpressionElements;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinExpression.BooleanLiteral;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralBoolean;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinLiteralBooleanExpression extends TwinLiteralExpression<Boolean, LiteralBoolean> implements BooleanLiteral {


	public TwinLiteralBooleanExpression(LiteralBoolean sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		this.setValue(this.getSysmlElement().isValue());
	}
}
