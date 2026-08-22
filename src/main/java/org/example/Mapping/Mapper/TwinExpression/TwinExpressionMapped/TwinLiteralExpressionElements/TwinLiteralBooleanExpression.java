package org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements;

import lombok.ToString;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralBoolean;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinLiteralBooleanExpression extends TwinLiteralExpression<Boolean, LiteralBoolean> {


	public TwinLiteralBooleanExpression(LiteralBoolean sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		this.setValue(this.getSysmlElement().isValue());
	}
}
