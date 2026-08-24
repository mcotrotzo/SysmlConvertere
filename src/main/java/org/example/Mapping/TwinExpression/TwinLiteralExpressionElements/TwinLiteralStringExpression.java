package org.example.Mapping.TwinExpression.TwinLiteralExpressionElements;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinExpression.StringLiteral;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralString;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinLiteralStringExpression extends TwinLiteralExpression<String, LiteralString> implements StringLiteral {

	public TwinLiteralStringExpression(LiteralString sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		this.setValue(this.getSysmlElement().getValue());
	}
}
