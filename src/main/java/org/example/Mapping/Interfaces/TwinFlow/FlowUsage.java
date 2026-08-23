package org.example.Mapping.Interfaces.TwinFlow;

import org.example.Mapping.Interfaces.Base.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.Reference;


public interface FlowUsage extends Flow, Usage {

	Reference<? extends  TwinAttributeUsage> getSource();
	Reference<? extends  TwinAttributeUsage> getTarget();
}
