package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseType;

import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;

import java.util.List;

public interface UserLibrary extends Package {
	List<? extends BaseFunction> getDefinitions();
	List<? extends CustomCalculation> getCustomCalculations();
	List<? extends TwinBaseType<Usage>> getBaseTypeDefinitions();
	List<? extends CustomType<Usage>> getCustomTypeDefinitions();
	List<? extends QueryFlow<Usage>> getQueryDefinitions();
}
