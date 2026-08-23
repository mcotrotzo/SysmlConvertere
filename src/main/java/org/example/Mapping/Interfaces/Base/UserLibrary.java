package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinBaseTypeDefinition;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.Definition.CustomTypeDefinition;
import org.example.Mapping.Interfaces.Query;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;

import java.util.List;

public interface UserLibrary extends Package {
	List<? extends BaseFunction> getDefinitions();
	List<? extends CustomCalculation> getCustomCalculations();
	List<? extends TwinBaseTypeDefinition> getBaseTypeDefinitions();
	List<? extends CustomTypeDefinition> getCustomTypeDefinitions();
	List<? extends Query> getQueryDefinitions();
}
