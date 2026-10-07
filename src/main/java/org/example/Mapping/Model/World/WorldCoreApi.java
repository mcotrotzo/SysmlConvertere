package org.example.Mapping.Model.World;

import org.example.Mapping.Model.Flow.FederationFlowUsage;
import org.example.Mapping.Model.Twin.TwinUsage;
import org.example.Mapping.Model.Type.CoreApi;

import java.util.List;

public interface WorldCoreApi extends CoreApi<WorldCore> {

	default List<TwinUsage> getTwins() { return getCore().getTwins(); }
	default List<FederationFlowUsage> getFederationFlows() { return getCore().getFederatedLinks(); }
}
