package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.Base.UserLibrary;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseType;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.Definition.CustomType;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlowDefinition;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;
import org.example.Mapping.NewVersion.*;
import org.example.Mapping.NewVersion.TwinFlow.Definition.QueryFlowMappedDefinition;
import org.example.Mapping.TwinFunction.BaseFunction;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeMapped;
import org.example.Mapping.TwinAttributeMapped.CustomTypeMapped.CustomAttributeMapped;
import org.example.Mapping.TwinFunction.CustomCalculationMapped;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Package;

import java.util.ArrayList;
import java.util.List;

@PackageTypeMeta(value = LibraryPackageNames.USER_LIBRARY)
public class UserLibraryMapped extends PackageElementType implements UserLibrary {
	List<BaseFunction> baseFunctionDefinitions = new ArrayList<>();
	List<CustomCalculationMapped> customCalculationDefinitions = new ArrayList<>();
	List<CustomAttributeMapped> customTypeDefinitions = new ArrayList<>();
	List<TwinBaseAttributeMapped> baseTypedDefinitions = new ArrayList<>();
	List<QueryFlowMappedDefinition> queryDefinitions = new ArrayList<>();

	public UserLibraryMapped(Package sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		baseFunctionDefinitions = context.mapOwnedNamespace(this, Classifier.class, BaseFunction.class);
		baseTypedDefinitions = context.mapOwnedNamespace(this, Classifier.class, TwinBaseAttributeMapped.class);
		customCalculationDefinitions = context.mapOwnedNamespace(this, Classifier.class, CustomCalculationMapped.class);
		customTypeDefinitions = context.mapOwnedNamespace(this, Classifier.class, CustomAttributeMapped.class);
		queryDefinitions = context.mapOwnedNamespace(this, Classifier.class, QueryFlowMappedDefinition.class);
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
	public List<? extends TwinBaseType> getBaseTypeDefinitions() {
		return baseTypedDefinitions;
	}

	@Override
	public List<? extends CustomType> getCustomTypeDefinitions() {
		return customTypeDefinitions;
	}

	@Override
	public List<? extends QueryFlowDefinition> getQueryDefinitions() {
		return queryDefinitions;
	}
}
