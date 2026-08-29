package org.example.Mapping.NewVersion.NameSpace.NameSpaceImport;

import org.example.Mapping.Interfaces.Base.TypeKind.NamespaceKind;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.PackageElementType;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Import;
import org.omg.sysml.lang.sysml.MembershipImport;
import org.omg.sysml.lang.sysml.Namespace;
import org.omg.sysml.lang.sysml.NamespaceImport;
import org.omg.sysml.lang.sysml.Package;

@MappedMetaclass
public class ImportMapped extends MappedNamespaceElement<Import, NamespaceKind>
		implements org.example.Mapping.Interfaces.Base.Import {

	MappedReference<PackageElementType> importPackages;

	public ImportMapped(Import sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		Package importedPackage = null;

		if (getSysmlElement() instanceof NamespaceImport namespaceImport
				&& namespaceImport.getImportedNamespace() instanceof Package packageType) {
			importedPackage = packageType;
		} else if (getSysmlElement() instanceof MembershipImport membershipImport
				&& membershipImport.getImportedMembership() != null
				&& membershipImport.getImportedMembership().getMemberElement() instanceof Package packageType) {
			importedPackage = packageType;
		}

		if (importedPackage == null) {
			return;
		}

		if (context.getUtils().isFromStandardLibrary(importedPackage)) {
			return;
		}

		importPackages =
				context.mapReference(
						importedPackage,
						PackageElementType.class
				);
	}

	private Package enclosingPackage(Element element) {
		Element current = element;

		while (current != null) {
			if (current instanceof Package packageType) {
				return packageType;
			}

			Namespace owner = current.getOwningNamespace();
			current = owner;
		}

		return null;
	}

	public MappedReference<PackageElementType> getImportPackages() {
		return importPackages;
	}
}