package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.TwinStringMapped;

import java.util.List;

public interface Strategy extends Model {
	List<TwinAttribute> getInputs();

	List<TwinAttribute> getOutputs();

	List<TwinStringMapped> getLambdaPath();
}
