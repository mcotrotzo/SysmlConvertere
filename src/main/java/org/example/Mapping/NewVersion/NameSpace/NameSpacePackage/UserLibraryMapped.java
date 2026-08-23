package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Interfaces.Base.UserLibrary;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinBaseTypeDefinition;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.Definition.CustomTypeDefinition;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;
import org.example.Mapping.NewVersion.*;
import org.example.Mapping.TwinFunction.BaseFunction;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeDefinitionMapped;
import org.example.Mapping.TwinAttributeMapped.CustomTypeMapped.CustomAttributeMappedDefinition;
import org.example.Mapping.TwinFunction.CustomCalculationMapped;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.Package;

import java.util.ArrayList;
import java.util.List;

@PackageTypeMeta(value = LibraryPackageNames.USER_LIBRARY)
public class UserLibraryMapped extends PackageElementType implements UserLibrary {
	List<BaseFunction> baseFunctionDefinitions = new ArrayList<>();
	List<CustomCalculationMapped> customCalculationDefinitions = new ArrayList<>();
	List<CustomAttributeMappedDefinition> customTypeDefinitions = new ArrayList<>();
	List<TwinBaseAttributeDefinitionMapped> baseTypedDefinitions = new ArrayList<>();
	List<AbstractMappedQuery> queryDefinitions = new ArrayList<>();

	public UserLibraryMapped(Package sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		baseFunctionDefinitions = context.mapOwnedNamespace(this, Definition.class, BaseFunction.class);
		baseTypedDefinitions = context.mapOwnedNamespace(this, Definition.class, TwinBaseAttributeDefinitionMapped.class);
		customCalculationDefinitions = context.mapOwnedNamespace(this, Definition.class, CustomCalculationMapped.class);
		customTypeDefinitions = context.mapOwnedNamespace(this, Definition.class, CustomAttributeMappedDefinition.class);
		queryDefinitions = context.mapOwnedNamespace(this, Definition.class, AbstractMappedQuery.class);
	}

	@Override
	protected List<Class<? extends PackageElementType>> getCanImport() {
		return List.of(UserLibraryMapped.class);
	}

	@Override
	public List<? extends org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction> getDefinitions() {
		return baseFunctionDefinitions;
	}

	@Override
	public List<? extends CustomCalculation> getCustomCalculations() {
		return customCalculationDefinitions;
	}

	@Override
	public List<? extends TwinBaseTypeDefinition> getBaseTypeDefinitions() {
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
