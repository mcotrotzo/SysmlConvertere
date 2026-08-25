package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;

public interface NameSpace<T extends TypeKindNamespace> extends Model<T>{
	@Override
	default Context getContext() {
		return getParent().isPresent()
				? getParent().get().getContext()
				: Context.NAMESPACE;
	}

}
