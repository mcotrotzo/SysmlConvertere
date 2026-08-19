package org.example.Mapping.NewVersion.Packages;

import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.Import;
import org.omg.sysml.lang.sysml.Package;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
public class ImportMapped extends MappedNamespaceElement<Import>  {


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
