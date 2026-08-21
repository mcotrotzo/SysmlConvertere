package org.example.Mapping.NewVersion.NameSpace.NameSpaceImport;

import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.PackageElementType;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.Import;
import org.omg.sysml.lang.sysml.Package;

@MappedMetaclass
public class ImportMapped extends MappedNamespaceElement<Import> {


	MappedReference<PackageElementType> importPackages;

	public ImportMapped(Import sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		if(getSysmlElement().getImportOwningNamespace() instanceof Package packageType){
			importPackages = context.mapReference(packageType, PackageElementType.class);
		}

		if(importPackages == null){
			throw new MappingException("ImportMapped: importPackages is null for " + getSysmlElement().getName() + "Each file needs to import TwinDefLibrary or UserLibrary");
		}
	}
}
