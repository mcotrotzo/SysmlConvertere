package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.ToString;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralInteger;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinLiteralIntegerExpression extends TwinLiteralExpression<Integer, LiteralInteger> {

	public TwinLiteralIntegerExpression(LiteralInteger sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		this.setValue(this.getSysmlElement().getValue());
	}
}
