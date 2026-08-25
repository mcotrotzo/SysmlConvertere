package org.example.Mapping.Interfaces.BaseTaxonomy;


import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;

import java.util.List;

public interface PrescriptiveModel <T extends TypeKind> extends Type<T> {

	List<Strategy<Usage>> getPrescriptiveStrategies();
	List<Flow<Usage>> getPrescriptiveFlows();
	@Override
	default Context getContext() {
		return Context.PRESCRIPTIVE;
	}
}
