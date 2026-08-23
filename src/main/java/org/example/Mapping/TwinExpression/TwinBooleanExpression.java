package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.BooleanExpression;
import org.omg.sysml.lang.sysml.Predicate;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinBooleanExpression extends TwinExpression<BooleanExpression> {

	private Predicate predicate;

	public TwinBooleanExpression(BooleanExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

	}
}
