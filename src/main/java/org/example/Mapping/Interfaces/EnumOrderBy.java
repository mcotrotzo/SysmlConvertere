package org.example.Mapping.Interfaces;

import lombok.Getter;

public enum EnumOrderBy implements TwinEnum{

	ASC("ASCENDING"),
	DESC("DESCENDING");
	@Getter
	private final String stringRepresentation;

	EnumOrderBy(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

}