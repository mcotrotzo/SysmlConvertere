package org.example.Mapping.Interfaces.Base.TypeKind;

import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;

public final class Usage implements TypeKind {
	public static final Usage INSTANCE = new Usage();

	private Usage() {
	}


	@Override
	public Class<? extends Element> sysmlType() {
		return Feature.class;
	}
}
