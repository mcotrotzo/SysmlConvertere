package org.example.Mapping.NewVersion.Packages;

import org.example.Mapping.Interfaces.Package;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Import;
import org.omg.sysml.lang.sysml.Membership;

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
