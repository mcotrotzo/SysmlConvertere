package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.Abstract.MappedReference;

import java.util.List;

public interface Succession extends Model {
	List<Reference<? extends Action>> getActionList();
}
