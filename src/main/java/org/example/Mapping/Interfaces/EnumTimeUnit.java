package org.example.Mapping.Interfaces;

import lombok.Getter;

public enum EnumTimeUnit implements TwinEnum{

	MICROSECOND("MICROSECOND"),
	MILLISECOND("MILLISECOND"),
	HOUR("HOUR"),
	SECOND("SECOND"),
	MINUTE("MINUTE"),
	DAY("DAY"),
	YEAR("YEAR");
	@Getter
	private final String stringRepresentation;

	EnumTimeUnit(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
}
