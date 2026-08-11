package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.Abstract.MappedReference;

import java.util.List;

public interface Calculation extends Expression {
	Reference<? extends Function> getCalledFunction();

	List<Expression> getArguments();
}
