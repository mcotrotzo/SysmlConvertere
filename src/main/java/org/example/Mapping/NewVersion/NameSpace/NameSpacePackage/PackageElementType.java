package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.Base.Package;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.NameSpace.NameSpaceImport.ImportMapped;
import org.omg.sysml.lang.sysml.Import;

import java.util.List;

public abstract class PackageElementType extends MappedNamespaceElement<org.omg.sysml.lang.sysml.Package> implements Package {

	List<ImportMapped> elementTypeList;
	public PackageElementType(org.omg.sysml.lang.sysml.Package sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		elementTypeList = context.mapOwnedNamespace(this,Import.class,ImportMapped.class);

		for(var s:elementTypeList){
			for(var t:this.getCanImport()){
				if(!s.getClass().isInstance(t)){
					throw new MappingException("Package "+this.getSysmlElement().getName()+" can not import "+s.getSysmlElement().getName()+" of type "+s.getClass().getSimpleName());
				}
			}
		}
	}

	protected abstract List<Class<? extends PackageElementType>> getCanImport();
}
