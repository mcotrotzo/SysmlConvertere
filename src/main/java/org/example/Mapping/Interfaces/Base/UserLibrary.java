package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseType;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;

import java.util.List;

public interface UserLibrary extends Package {
	List<? extends BaseFunction> getDefinitions();

	List<? extends CustomCalculation> getCustomCalculations();

	List<? extends TwinBaseType<Definition>> getBaseTypeDefinitions();

	List<? extends CustomType<Definition>> getCustomTypeDefinitions();

	List<? extends QueryFlow<Definition>> getQueryDefinitions();
}
