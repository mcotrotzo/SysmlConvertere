package org.example.Util;

public enum LibraryPackageNames {
	TWIN_DEF_LIBRARY("TwinDefLibrary"),
	USER_LIBRARY("UserLibrary");

	private final String namespace;

	LibraryPackageNames(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public String toString() {
		return namespace;
	}
}
