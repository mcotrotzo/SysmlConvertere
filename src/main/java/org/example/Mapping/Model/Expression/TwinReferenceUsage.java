package org.example.Mapping.Model.Expression;

import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Expression;

public abstract class TwinReferenceUsage<T extends Expression> extends TwinExpressionUsage<T> {

	@Getter
	protected Usage<?, ?, ?> target;

	protected TwinReferenceUsage(T sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
