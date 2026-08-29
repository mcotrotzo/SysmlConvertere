package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.UserLibrary;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseType;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinFlow.Definition.QueryFlowMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeMapped;
import org.example.Mapping.TwinAttributeMapped.CustomTypeMapped.CustomAttributeMapped;

import org.example.Mapping.TwinFunction.BaseFunction;
import org.example.Mapping.TwinFunction.CustomCalculationMapped;
import org.example.Util.LibraryPackageNames;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.LibraryPackage;
import org.omg.sysml.lang.sysml.Package;

import java.util.ArrayList;
import java.util.List;

@PackageTypeMeta
public class UserLibraryMapped<T extends Package> extends PackageElementType implements UserLibrary {
	List<CustomCalculationMapped> customCalculationDefinitions = new ArrayList<>();
	List<CustomAttributeMapped<Definition>> customTypeDefinitions = new ArrayList<>();
	List<? extends TwinBaseAttributeMapped<Definition>> baseTypedDefinitions = new ArrayList<>();
	List<QueryFlowMapped<Definition>> queryDefinitions = new ArrayList<>();
	List<? extends BaseFunction> baseFunctionDefinitions = new ArrayList<>();
	public UserLibraryMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		baseFunctionDefinitions = context.mapOwnedNamespace(this, Classifier.class, rawClassOf(BaseFunction.class));
		baseTypedDefinitions = context.mapOwnedNamespace(this, Classifier.class, rawClassOf(TwinBaseAttributeMapped.class));
		customCalculationDefinitions = context.mapOwnedNamespace(this, Classifier.class, rawClassOf(CustomCalculationMapped.class));
		customTypeDefinitions = context.mapOwnedNamespace(this, Classifier.class, rawClassOf(CustomAttributeMapped.class));
		queryDefinitions = context.mapOwnedNamespace(this, Classifier.class, rawClassOf(QueryFlowMapped.class));
	}

	@Override
	protected List<Class<? extends PackageElementType>> getCanImport() {
		return List.of(UserLibraryMapped.class);
	}
	protected LibraryPackageNames getLibraryPackageName() {
		return LibraryPackageNames.USER_LIBRARY;
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
	public List<? extends TwinBaseType<Definition>> getBaseTypeDefinitions() {
		return new ArrayList<>(baseTypedDefinitions);
	}

	@Override
	public List<? extends CustomType<Definition>> getCustomTypeDefinitions() {
		return new ArrayList<>(customTypeDefinitions);
	}

	@Override
	public List<? extends QueryFlow<Definition>> getQueryDefinitions() {
		return new ArrayList<>(queryDefinitions);
	}


}
