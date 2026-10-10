package org.example.Mapping.Model.Action;

import Model.Predefined.MetaClasses.Action.ActionCoreApi;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;

import java.util.List;

public interface ActionBlockCoreApi<C extends ActionBlockCore> extends ActionCoreApi<C> {
	@Override default List<TwinAttributeUsage<?, ?>> getInputs() { return getCore().getInputs(); }
	@Override default List<TwinAttributeUsage<?, ?>> getOutputs() { return getCore().getOutputs(); }
	default List<TwinAttributeUsage<?, ?>> getLocalAttributes() { return getCore().getLocalAttributes(); }
}
