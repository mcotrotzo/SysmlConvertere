package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.*;
import org.example.Mapping.NewVersion.*;
import org.example.Mapping.NewVersion.BaseFunction;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.Package;

import java.util.ArrayList;
import java.util.List;

@PackageTypeMeta(value = LibraryPackageNames.USER_LIBRARY)
public class UserLibraryMapped extends PackageElementType implements UserLibrary {
	List<BaseFunction> baseFunctionDefinitions = new ArrayList<>();
	List<CustomCalculationMapped> customCalculationDefinitions = new ArrayList<>();
	List<CustomAttributeMappedDefintion> customTypeDefinitions = new ArrayList<>();
	List<BaseAttributeDefinitionMapped> baseTypedDefinitions = new ArrayList<>();
	List<AbstractMappedQuery> queryDefinitions = new ArrayList<>();

	public UserLibraryMapped(Package sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		baseFunctionDefinitions = context.mapOwnedNamespace(this, Definition.class, BaseFunction.class);
		baseTypedDefinitions = context.mapOwnedNamespace(this, Definition.class, BaseAttributeDefinitionMapped.class);
		customCalculationDefinitions = context.mapOwnedNamespace(this, Definition.class, CustomCalculationMapped.class);
		customTypeDefinitions = context.mapOwnedNamespace(this, Definition.class, CustomAttributeMappedDefintion.class);
		queryDefinitions = context.mapOwnedNamespace(this, Definition.class, AbstractMappedQuery.class);
	}

	@Override
	protected List<Class<? extends PackageElementType>> getCanImport() {
		return List.of(UserLibraryMapped.class);
	}

	@Override
	public List<? extends org.example.Mapping.Interfaces.BaseFunction> getDefinitions() {
		return baseFunctionDefinitions;
	}

	@Override
	public List<? extends CustomCalculation> getCustomCalculations() {
		return customCalculationDefinitions;
	}

	@Override
	public List<? extends BaseTypeDefinition> getBaseTypeDefinitions() {
		return baseTypedDefinitions;
	}

	@Override
	public List<? extends CustomTypeDefinition> getCustomTypeDefinitions() {
		return customTypeDefinitions;
	}

	@Override
	public List<? extends Query> getQueryDefinitions() {
		return queryDefinitions;
	}
}
