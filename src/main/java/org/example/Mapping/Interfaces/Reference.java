package org.example.Mapping.Interfaces;

public interface Reference<T extends Model> {

	T getReferent();

	String getTargetId();
}
