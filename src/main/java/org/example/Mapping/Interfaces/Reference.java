package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.Base.Model;

public interface Reference<T extends Model> {

	T getReferent();

	String getTargetId();
}
