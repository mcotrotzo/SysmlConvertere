package org.example.Mapping.Interfaces.TwinFlow;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.TwinAction.Definition.ActionDefinition;

import java.util.Set;

public interface FlowDefinition extends Flow, ActionDefinition {
	Set<Context> sourceContexts();

	Set<Context> targetContexts();
}
