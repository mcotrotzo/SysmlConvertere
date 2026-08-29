package org.example.Util;

public enum LibraryPackageNames {
	TWIN_DEF_LIBRARY("TwinLibrary"),
	USER_LIBRARY("UserLibrary"),
	DT_LIBRARY("DTLibrary");

	private final String namespace;

	LibraryPackageNames(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public String toString() {
		return namespace;
	}
}
