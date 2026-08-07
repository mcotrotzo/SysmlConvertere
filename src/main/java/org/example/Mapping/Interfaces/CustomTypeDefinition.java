package org.example.Mapping.Interfaces;

import java.util.List;

public interface CustomTypeDefinition extends Model{

	List<TwinAttribute> getFields();
	List<Reference<? extends CustomTypeDefinition>> getParents();
}
