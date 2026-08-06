package org.example.Mapping.Interfaces;

import java.util.List;

public interface Query extends Function {
	List<TwinAttribute> getTwinAttribute();

	List<TwinIntegerAttribute> getSince();

	List<TwinAttribute> getSinceUnit();

	List<TwinAttribute> getOrderBy();

	List<TwinBooleanAttribute> getFilterExpression();

	List<TwinAttribute> getResult();

	List<TwinIntegerAttribute> getLimit();


}
