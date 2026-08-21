package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.Optional;

public interface Query extends Function {
	TwinAttributeUsage getTwinAttribute();

	Optional<TwinIntegerAttributeUsage> getSince();

	Optional<EnumTimeUnit> getSinceUnit();

	Optional<EnumOrderBy> getOrderBy();

	Optional<TwinBooleanAttributeUsage> getFilterExpression();

	Optional<TwinIntegerAttributeUsage> getLimit();


}
