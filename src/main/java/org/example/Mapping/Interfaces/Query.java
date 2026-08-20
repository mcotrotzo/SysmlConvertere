package org.example.Mapping.Interfaces;

import java.util.List;
import java.util.Optional;

public interface Query extends Function {
	TwinAttribute getTwinAttribute();

	Optional<TwinIntegerAttribute> getSince();

	Optional<EnumTimeUnit> getSinceUnit();

	Optional<EnumOrderBy> getOrderBy();

	Optional<TwinBooleanAttribute> getFilterExpression();

	Optional<TwinIntegerAttribute> getLimit();


}
