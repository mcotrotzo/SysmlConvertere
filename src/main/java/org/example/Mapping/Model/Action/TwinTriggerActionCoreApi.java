package org.example.Mapping.Model.Action;

import java.util.Optional;

public interface TwinTriggerActionCoreApi<C extends ActionBlockCore & TriggerCore> extends ActionBlockCoreApi<C> {
	default Optional<TwinTriggerUsage> getTrigger() { return getCore().getTrigger(); }
}
