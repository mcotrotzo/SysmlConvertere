package org.example.Mapping.Interfaces.TwinEnumPackage;

import lombok.Getter;

public enum EnumFederationLink implements TwinEnum {

	PUSH("PUSH"),
	PULL("PULL"),
	SHARED_MEMORY("SHARED_MEMORY"),
	PUBSUB("PUBSUB"),
	NEGOTIATION("NEGOTIATION");
	@Getter
	private final String stringRepresentation;

	EnumFederationLink(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

}