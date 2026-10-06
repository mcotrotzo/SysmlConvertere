package org.example.Mapping;

import lombok.Getter;

public enum CustomStrategyType implements TwinEnum {
	CONTAINER("CONTAINER"), LAMBDA("LAMBDA");

	@Getter
	private final String stringRepresentation;

	CustomStrategyType(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
}