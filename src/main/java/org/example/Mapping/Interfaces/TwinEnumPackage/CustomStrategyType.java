package org.example.Mapping.Interfaces.TwinEnumPackage;

import lombok.Getter;

public enum CustomStrategyType  implements TwinEnum {
	CONTAINER("CONTAINER"),
	LAMBDA("LAMBDA");

	@Getter
	private final String stringRepresentation;

	CustomStrategyType(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
}

