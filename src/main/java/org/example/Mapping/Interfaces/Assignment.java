package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.Abstract.MappedReference;

public interface Assignment extends Action {
	MappedReference<? extends TwinAttribute> getTarget();

	Expression getValue();
}
