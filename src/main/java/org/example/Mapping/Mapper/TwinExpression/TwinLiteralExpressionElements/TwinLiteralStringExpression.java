package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.ToString;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralString;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinLiteralStringExpression extends TwinLiteralExpression<String, LiteralString> {

	public TwinLiteralStringExpression(LiteralString sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		this.setValue(this.getSysmlElement().getValue());
	}
}
