package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.Base.Package;
import org.example.Mapping.Interfaces.Base.TypeKind.NamespaceKind;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.NameSpace.NameSpaceImport.ImportMapped;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.Import;

import java.util.List;

public abstract class PackageElementType extends MappedNamespaceElement<org.omg.sysml.lang.sysml.Package, NamespaceKind> implements Package {

	List<ImportMapped> elementTypeList;

	public PackageElementType(org.omg.sysml.lang.sysml.Package sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		elementTypeList = context.mapOwnedImports(this);

		for (ImportMapped importMapped : elementTypeList) {
			if (importMapped.getImportPackages() == null) {
				continue;
			}

			PackageElementType importedPackage =
					importMapped.getImportPackages().getReferent();

			boolean allowed = getCanImport().stream()
					.anyMatch(type -> type.isInstance(importedPackage));
			System.out.println(importedPackage.getSysmlElement().getQualifiedName());
			System.out.println(getLibraryPackageName().toString());
			allowed |= importedPackage.getSysmlElement().getQualifiedName().equals(getLibraryPackageName().toString());
			if (!allowed) {
				throw new MappingException(
						"Package "
								+ getSysmlElement().getName()
								+ " can not import "
								+ importedPackage.getSysmlElement().getName()
								+ " of type "
								+ importedPackage.getClass().getSimpleName()
				);
			}
		}
	}
	protected abstract LibraryPackageNames getLibraryPackageName();

	protected abstract List<Class<? extends PackageElementType>> getCanImport();
}
