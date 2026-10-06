package org.example.Mapping.Model.Expression;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.example.Mapping.Model.Type.EmptyCore;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Expression;

/** Base of all mapped expressions. Expressions only exist as usages; a typing definition (e.g. a user calc) is kept as plain Definition. */
public abstract class TwinExpressionUsage<T extends Expression> extends Usage<EmptyCore, T, Definition> {
	protected TwinExpressionUsage(T sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, mapper), mapper, Definition.class);
	}
}
