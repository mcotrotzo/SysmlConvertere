package org.example.Util;

public enum TwinLibraryNamespace {


	TwinLibraryElement("TwinLibraryPackage::TwinLibraryElement"),

	TwinLibraryConnection("TwinLibraryPackage::TwinLibraryElementConnection"),

	TwinLibraryCalcDef("TwinLibraryPackage::TwinLibraryElementCalcDef"),

	TwinLibraryActionDef("TwinLibraryPackage::TwinLibraryElementActionDef"),

	TwinLibraryAttributeDef("TwinLibraryPackage::TwinLibraryElementAttributeDef"),

	TwinLibraryPortDef("TwinLibraryPackage::TwinLibraryElementPortDef"),

	TwinLibraryStateDef("TwinLibraryPackage::TwinLibraryElementStateDef");

	private final String namespace;

	TwinLibraryNamespace(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public String toString() {
		return namespace;
	}
}