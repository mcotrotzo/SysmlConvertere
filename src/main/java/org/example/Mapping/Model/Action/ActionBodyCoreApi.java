package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Type.CoreApi;

import java.util.List;

public interface ActionBodyCoreApi<C extends ActionBodyCore> extends CoreApi<C> {
	default List<TwinActionUsage<?, ?, ?>> getActions() { return getCore().getActions(); }
	default List<TwinSuccessionUsage> getSuccessions() { return getCore().getSuccessions(); }
}
