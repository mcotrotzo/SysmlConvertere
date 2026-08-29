package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.Base.DTLibrary;
import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinFunction.CustomCalculationMapped;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.LibraryPackage;

import java.util.ArrayList;
import java.util.List;

@PackageTypeMeta
public class LibraryPackagesMapped extends PackageElementType implements DTLibrary {
	List<? extends MappedElement<?,Definition>> libraryDefinitions = new ArrayList<>();


	public LibraryPackagesMapped(LibraryPackage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		libraryDefinitions = context.mapOwnedNamespace(this, org.omg.sysml.lang.sysml.Classifier.class, rawClassOf(MappedElement.class));

	}

	@Override
	protected LibraryPackageNames getLibraryPackageName() {
		return LibraryPackageNames.DT_LIBRARY;
	}

	@Override
	protected List<Class<? extends PackageElementType>> getCanImport() {
		return List.of(LibraryPackagesMapped.class);
	}

	@Override
	public List<? extends Type<Definition>> getLibraryDefinitions() {
		return libraryDefinitions;
	}

}
