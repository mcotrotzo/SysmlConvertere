package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.Base.TwinDefLibrary;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.FullTwin.Twin;
import org.example.Mapping.NewVersion.FullTwinMapped.TwinMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Package;

import java.util.ArrayList;
import java.util.List;

@PackageTypeMeta
public class TwinDefLibraryMapped extends PackageElementType implements TwinDefLibrary {

	List<TwinMapped<Definition>> twins = new ArrayList<>();

	public TwinDefLibraryMapped(Package sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		twins = context.mapOwnedNamespace(this, Element.class, rawClassOf(TwinMapped.class));
	}

	@Override
	protected List<Class<? extends PackageElementType>> getCanImport() {
		return List.of(UserLibraryMapped.class);
	}

	protected LibraryPackageNames getLibraryPackageName() {
		return LibraryPackageNames.TWIN_DEF_LIBRARY;
	}


	@Override
	public List<Twin<Definition>> getTwins() {
		return new ArrayList<>(twins);
	}
}
