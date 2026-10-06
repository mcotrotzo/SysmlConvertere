package org.example.Mapping.Model.Taxonomy.Shadow;

import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCoreApi;
import org.example.Mapping.Model.Database.DatabaseUsage;

import java.util.List;

public interface ShadowCoreApi extends TaxonomyCoreApi<ShadowCore> {
	default List<DatabaseUsage<?, ?>> getDatabases() { return getCore().getDatabases(); }
}
