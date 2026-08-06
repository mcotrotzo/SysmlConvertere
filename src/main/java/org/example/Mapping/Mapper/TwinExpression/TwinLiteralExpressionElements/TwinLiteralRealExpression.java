package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.ToString;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralRational;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinLiteralRealExpression extends TwinLiteralExpression<Double, LiteralRational> {

	public TwinLiteralRealExpression(LiteralRational sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		this.setValue(this.getSysmlElement().getValue());
	}
}
