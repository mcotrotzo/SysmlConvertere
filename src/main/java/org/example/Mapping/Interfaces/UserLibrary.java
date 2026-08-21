package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.Base.Package;

import java.util.List;

public interface UserLibrary extends Package {
	List<? extends BaseFunction> getDefinitions();
	List<? extends CustomCalculation> getCustomCalculations();
	List<? extends BaseTypeDefinition> getBaseTypeDefinitions();
	List<? extends CustomTypeDefinition> getCustomTypeDefinitions();
	List<? extends Query> getQueryDefinitions();
}
