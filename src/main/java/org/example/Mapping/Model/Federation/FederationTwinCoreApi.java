package org.example.Mapping.Model.Federation;

import org.example.Mapping.Model.Flow.FederationFlowUsage;
import org.example.Mapping.Model.Type.CoreApi;

import java.util.List;

public interface FederationTwinCoreApi extends CoreApi<FederationTwinCore> {
	default List<FederationFlowUsage> getFederationFlows() { return getCore().getFederationFlows(); }
}
