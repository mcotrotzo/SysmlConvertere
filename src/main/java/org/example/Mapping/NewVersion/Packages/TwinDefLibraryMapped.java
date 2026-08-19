package org.example.Mapping.NewVersion.Packages;

import org.example.Mapping.Interfaces.Twin;
import org.example.Mapping.Interfaces.TwinDefLibrary;
import org.example.Mapping.NewVersion.MappedTwin;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryNameSpaces;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Package;

import java.util.ArrayList;
import java.util.List;

@PackageTypeMeta(value = LibraryPackageNames.TWIN_DEF_LIBRARY)
public class TwinDefLibraryMapped extends PackageElementType implements TwinDefLibrary {

	List<MappedTwin> twins = new ArrayList<>();

	public TwinDefLibraryMapped(Package sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		twins = context.mapOwnedNamespace(this, Element.class, MappedTwin.class);
	}

	@Override
	protected List<Class<? extends PackageElementType>> getCanImport() {
		return List.of(UserLibraryMapped.class);
	}


	@Override
	public List<Twin> getTwins() {
		return List.of();
	}
}
