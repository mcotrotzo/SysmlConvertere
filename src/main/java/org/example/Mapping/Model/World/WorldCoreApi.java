package org.example.Mapping.Model.World;


import Model.Core.CoreApi;
import org.example.Mapping.Model.Flow.FederationFlowUsage;
import org.example.Mapping.Model.Twin.TwinUsage;
import java.util.List;

public interface WorldCoreApi extends CoreApi<WorldCore> {

	default List<TwinUsage> getTwins() { return getCore().getTwins(); }
	default List<FederationFlowUsage> getFederationFlows() { return getCore().getFederatedLinks(); }
}
