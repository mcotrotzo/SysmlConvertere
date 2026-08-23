package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinAttribute.ConfigAttribute.TwinStringAttributeUsage;

import java.util.List;

public interface Strategy extends Model {
	List<TwinAttributeUsage> getInputs();

	List<TwinAttributeUsage> getOutputs();

	TwinStringAttributeUsage getLambdaPath();
}
