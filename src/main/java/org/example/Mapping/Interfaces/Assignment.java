package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.Abstract.MappedReference;

public interface Assignment extends Action {
	Reference<? extends TwinAttribute> getTarget();

	Expression getValue();
}
