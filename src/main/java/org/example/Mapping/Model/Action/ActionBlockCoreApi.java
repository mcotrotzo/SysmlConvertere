package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Attribute.TwinAttributeUsage;

import java.util.List;

public interface ActionBlockCoreApi<C extends ActionBlockCore> extends ActionBodyCoreApi<C> {
	default List<TwinAttributeUsage<?, ?>> getInputs() { return getCore().getInputs(); }
	default List<TwinAttributeUsage<?, ?>> getOutputs() { return getCore().getOutputs(); }
	default List<TwinAttributeUsage<?, ?>> getLocalAttributes() { return getCore().getLocalAttributes(); }

}
