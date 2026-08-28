package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.Base.Package;
import org.example.Mapping.Interfaces.Base.TypeKind.NamespaceKind;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.NameSpace.NameSpaceImport.ImportMapped;
import org.omg.sysml.lang.sysml.Import;

import java.util.List;

public abstract class PackageElementType extends MappedNamespaceElement<org.omg.sysml.lang.sysml.Package, NamespaceKind> implements Package {

	List<ImportMapped> elementTypeList;

	public PackageElementType(org.omg.sysml.lang.sysml.Package sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		System.out.println("Parsing PackageElementType: " + getSysmlElement().getName());
		System.out.println(this.sysmlElement.getOwnedImport());
		elementTypeList =
				context.mapOwnedNamespace(
						this,
						Import.class,
						ImportMapped.class
				);

		System.out.println(
				"Imports of "
						+ getSysmlElement().getName()
						+ ": "
						+ elementTypeList.size()
		);

		for (ImportMapped importMapped : elementTypeList) {
			PackageElementType importedPackage =
					importMapped.getImportPackages().getReferent();

			boolean allowed = getCanImport().stream()
					.anyMatch(type -> type.isInstance(importedPackage));

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
	protected abstract List<Class<? extends PackageElementType>> getCanImport();
}
